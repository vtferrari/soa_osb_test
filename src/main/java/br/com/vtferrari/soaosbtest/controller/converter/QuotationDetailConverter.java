package br.com.vtferrari.soaosbtest.controller.converter;

import br.com.vtferrari.soaosbtest.controller.resource.QuotationDetailResponse;
import br.com.vtferrari.soaosbtest.service.model.QuotationDetail;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class QuotationDetailConverter {

    public Mono<QuotationDetailResponse> convert(final QuotationDetail quotationDetail) {
        return Mono.just(QuotationDetailResponse.builder()
                .id(quotationDetail.getId())
                .cityName(quotationDetail.getCityName())
                .build());
    }
}
