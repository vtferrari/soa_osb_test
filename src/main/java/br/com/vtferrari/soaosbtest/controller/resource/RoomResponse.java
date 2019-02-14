package br.com.vtferrari.soaosbtest.controller.resource;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomResponse {

    private Long roomID;
    private String categoryName;
    private BigDecimal totalPrice;
    private PriceDetailResponse priceDetail;
}
