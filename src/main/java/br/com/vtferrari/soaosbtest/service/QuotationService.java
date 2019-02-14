package br.com.vtferrari.soaosbtest.service;

import br.com.vtferrari.soaosbtest.integration.HotelIntegration;
import br.com.vtferrari.soaosbtest.integration.converter.HotelIntegrationResponseConverter;
import br.com.vtferrari.soaosbtest.integration.resource.PrinceIntegrationResponse;
import br.com.vtferrari.soaosbtest.service.model.Quotation;
import br.com.vtferrari.soaosbtest.service.model.QuotationDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

@Service
@AllArgsConstructor
public class QuotationService {
    public static final BigDecimal TAX = BigDecimal.valueOf(1.70);
    private final HotelIntegration hotelIntegration;
    private final HotelIntegrationResponseConverter hotelIntegrationResponseConverter;

    public Flux<QuotationDetail> quote(final Quotation quotationRequest) {

        final var hotelIntegrationResponses = hotelIntegration.listHotelByCity(quotationRequest.getCityCode());
        return Flux
                .fromIterable(hotelIntegrationResponses)
                .parallel()
                .runOn(Schedulers.elastic())
                .map(hotelIntegrationResponseConverter::convert)
                .sequential();
    }

    private BigDecimal getTotalPrice(Quotation quotationRequest,, PrinceIntegrationResponse princeIntegrationResponse) {

        return getTotalStayPricePerAdult(quotationRequest, princeIntegrationResponse.getAdult())
                .add(getTotalStayPricePerChild(quotationRequest, princeIntegrationResponse.getChild()));
    }

    private BigDecimal getTotalStayPricePerAdult(final Quotation quotation, BigDecimal princePerAdult) {
        final BigDecimal stay = BigDecimal.valueOf(getStayInDays(quotation));
        return princePerAdult.multiply(stay).divide(TAX, RoundingMode.HALF_UP);
    }

    private BigDecimal getTotalStayPricePerChild(final Quotation quotation, BigDecimal princePerChild) {
        final BigDecimal stay = BigDecimal.valueOf(getStayInDays(quotation));
        return princePerChild.multiply(stay).divide(TAX, RoundingMode.HALF_UP);
    }

    private Long getStayInDays(final Quotation quotationRequest) {
        return Duration.between(quotationRequest.getCheckin(), quotationRequest.getCheckout()).toDays();
    }

}
