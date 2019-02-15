package br.com.vtferrari.soaosbtest.controller.resource;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PriceDetailResponse {

    private BigDecimal pricePerDayAdult;
    private BigDecimal pricePerDayChild;
}
