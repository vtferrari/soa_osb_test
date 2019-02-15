package br.com.vtferrari.soaosbtest.integration.resource;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PrinceIntegrationResponse {
    private BigDecimal adult;
    private BigDecimal child;

}
