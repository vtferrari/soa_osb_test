package br.com.vtferrari.soaosbtest.controller.resource;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuotationDetailResponse {

    private Long id;
    private String cityName;
    private List<RoomResponse> rooms;
}
