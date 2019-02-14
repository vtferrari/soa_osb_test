package br.com.vtferrari.soaosbtest.integration.resource;

import lombok.Data;

import java.util.List;

@Data
public class HotelIntegrationResponse {

    private Long id;
    private String name;
    private Long cityCode;
    private String cityName;
    private List<RoomIntegrationResponse> rooms;
}
