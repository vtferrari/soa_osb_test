package br.com.vtferrari.soaosbtest.controller.resource;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RoomResponse {

    private Long roomID;
    private String categoryName;
    private BigDecimal totalPrice;
    private PriceDetailResponse priceDetail;
}
