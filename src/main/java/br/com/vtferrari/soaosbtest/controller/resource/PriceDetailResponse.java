package br.com.vtferrari.soaosbtest.controller.resource;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceDetailResponse {

    private BigDecimal pricePerDayAdult;
    private BigDecimal pricePerDayChild;
}
