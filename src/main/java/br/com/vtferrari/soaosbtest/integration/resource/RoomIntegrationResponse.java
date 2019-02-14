package br.com.vtferrari.soaosbtest.integration.resource;

import lombok.Data;

@Data
public class RoomIntegrationResponse {
    private Long roomID;
    private String categoryName;
    private PrinceIntegrationResponse price;
}
