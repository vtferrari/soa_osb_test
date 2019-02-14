package br.com.vtferrari.soaosbtest.controller;

import br.com.vtferrari.soaosbtest.controller.resource.QuotationDetailResponse;
import br.com.vtferrari.soaosbtest.controller.resource.QuotationRequest;
import br.com.vtferrari.soaosbtest.controller.converter.QuotationDetailConverter;
import br.com.vtferrari.soaosbtest.controller.converter.QuotationRequestConverter;
import br.com.vtferrari.soaosbtest.service.QuotationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/quotations")
public class QuotationController {

    private final QuotationService quotationService;
    private final QuotationRequestConverter quotationRequestConverter;
    private final QuotationDetailConverter detailConverter;

    @GetMapping("/")
    public Flux<QuotationDetailResponse> quotation(@RequestBody final Mono<@Valid QuotationRequest> quotationRequest) {

        return quotationRequest
                .flatMap(quotationRequestConverter::convert)
                .flatMapMany(quotationService::quote)
                .flatMap(detailConverter::convert)
                ;
    }
}
