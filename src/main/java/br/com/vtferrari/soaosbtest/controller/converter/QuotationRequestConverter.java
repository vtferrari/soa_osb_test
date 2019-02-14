package br.com.vtferrari.soaosbtest.controller.converter;

import br.com.vtferrari.soaosbtest.controller.resource.QuotationRequest;
import br.com.vtferrari.soaosbtest.service.model.Quotation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class QuotationRequestConverter {

    public Mono<Quotation> convert(QuotationRequest quotationRequest) {
        return Mono.just(Quotation
                .builder()
                .cityCode(quotationRequest.getCityCode())
                .checkin(quotationRequest.getCheckin())
                .checkout(quotationRequest.getCheckout())
                .numberAdult(quotationRequest.getNumberAdult())
                .numberChild(quotationRequest.getNumberChild())
                .build());
    }
}
