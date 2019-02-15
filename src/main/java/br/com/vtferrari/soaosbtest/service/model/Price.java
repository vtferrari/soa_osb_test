package br.com.vtferrari.soaosbtest.service.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Price {
    private BigDecimal adult;
    private BigDecimal child;
}
