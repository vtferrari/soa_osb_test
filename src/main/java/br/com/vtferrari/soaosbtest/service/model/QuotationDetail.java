package br.com.vtferrari.soaosbtest.service.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuotationDetail {
    private Long id;
    private String cityName;
}
