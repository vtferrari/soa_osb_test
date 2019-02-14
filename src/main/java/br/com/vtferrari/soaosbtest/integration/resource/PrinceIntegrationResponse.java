package br.com.vtferrari.soaosbtest.integration.resource;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrinceIntegrationResponse {
    private BigDecimal adult;
    private BigDecimal child;

}
