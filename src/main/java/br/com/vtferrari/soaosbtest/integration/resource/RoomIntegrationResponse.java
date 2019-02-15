package br.com.vtferrari.soaosbtest.integration.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomIntegrationResponse {
    private Long roomID;
    private String categoryName;
    private PrinceIntegrationResponse price;
}
