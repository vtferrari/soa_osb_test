package br.com.vtferrari.soaosbtest.integration.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class HotelIntegrationResponse {

    private Long id;
    private String name;
    private Long cityCode;
    private String cityName;
    private List<RoomIntegrationResponse> rooms;
}
