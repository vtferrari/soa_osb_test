package br.com.vtferrari.soaosbtest.service;

import br.com.vtferrari.soaosbtest.integration.HotelIntegration;
import br.com.vtferrari.soaosbtest.integration.converter.HotelIntegrationResponseAdapter;
import br.com.vtferrari.soaosbtest.service.model.Quotation;
import br.com.vtferrari.soaosbtest.service.model.QuotationDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
@AllArgsConstructor
public class QuotationService {
    private final HotelIntegration hotelIntegration;
    private final HotelIntegrationResponseAdapter hotelIntegrationResponseAdapter;

    @Cacheable(value = "listHotelByCity", key = "#quotation.getCityCode()")
    public Flux<QuotationDetail> quote(final Quotation quotation) {

        final var hotelIntegrationResponses = hotelIntegration.listHotelByCity(quotation.getCityCode());
        log.info("Quote: " + hotelIntegrationResponses);
        return Flux
                .fromIterable(hotelIntegrationResponses)
                .parallel()
                .runOn(Schedulers.elastic())
                .map(hotels -> hotelIntegrationResponseAdapter.merge(hotels, quotation))
                .sequential()
                ;
    }

    @Cacheable(value = "getHotelById", key = "#hotelId")
    public Mono<QuotationDetail> quote(final Long hotelId, final Quotation quotation) {

        final var hotelIntegrationResponses = hotelIntegration.getHotelById(hotelId);
        log.info("Quote: " + hotelIntegrationResponses);
        return Flux
                .fromIterable(hotelIntegrationResponses)
                .single()
                .map(hotels -> hotelIntegrationResponseAdapter.merge(hotels, quotation))
                ;
    }
}
