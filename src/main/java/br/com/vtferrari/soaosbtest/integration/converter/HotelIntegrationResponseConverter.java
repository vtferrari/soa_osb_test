package br.com.vtferrari.soaosbtest.integration.converter;

import br.com.vtferrari.soaosbtest.integration.resource.HotelIntegrationResponse;
import br.com.vtferrari.soaosbtest.service.model.QuotationDetail;
import org.springframework.stereotype.Component;

@Component
public class HotelIntegrationResponseConverter {

    public QuotationDetail convert(final HotelIntegrationResponse hotelIntegrationResponse) {
        return QuotationDetail
                .builder()
                .id(hotelIntegrationResponse.getId())
                .cityName(hotelIntegrationResponse.getCityName())
                .build();
    }
}
