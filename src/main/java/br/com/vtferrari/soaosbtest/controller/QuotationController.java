package br.com.vtferrari.soaosbtest.controller;

import br.com.vtferrari.soaosbtest.controller.converter.QuotationDetailConverter;
import br.com.vtferrari.soaosbtest.controller.converter.QuotationRequestConverter;
import br.com.vtferrari.soaosbtest.controller.resource.QuotationDetailResponse;
import br.com.vtferrari.soaosbtest.controller.resource.QuotationRequest;
import br.com.vtferrari.soaosbtest.service.QuotationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/hotels")
public class QuotationController {

    private final QuotationService quotationService;
    private final QuotationRequestConverter quotationRequestConverter;
    private final QuotationDetailConverter detailConverter;

    @GetMapping("/quotations")
    public Flux<QuotationDetailResponse> quotation(@RequestBody final Mono<@Valid QuotationRequest> quotationRequest) {

        return quotationRequest
                .flatMap(quotationRequestConverter::convert)
                .flatMapMany(quotationService::quote)
                .flatMap(detailConverter::convert)
                ;
    }

    @GetMapping("/{hotelId}/quotations")
    public Mono<QuotationDetailResponse> quotation(@PathVariable Long hotelId, @RequestBody final Mono<@Valid QuotationRequest> quotationRequest) {

        return quotationRequest
                .flatMap(quotationRequestConverter::convert)
                .flatMap(quotation -> quotationService.quote(hotelId, quotation))
                .flatMap(detailConverter::convert)
                ;
    }
}
