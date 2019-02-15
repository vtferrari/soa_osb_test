package br.com.vtferrari.soaosbtest.integration.converter;

import br.com.vtferrari.soaosbtest.integration.resource.HotelIntegrationResponse;
import br.com.vtferrari.soaosbtest.integration.resource.PrinceIntegrationResponse;
import br.com.vtferrari.soaosbtest.service.model.Price;
import br.com.vtferrari.soaosbtest.service.model.Quotation;
import br.com.vtferrari.soaosbtest.service.model.QuotationDetail;
import br.com.vtferrari.soaosbtest.service.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelIntegrationResponseAdapter {

    public QuotationDetail merge(final HotelIntegrationResponse hotelIntegrationResponse, final Quotation quotation) {
        return QuotationDetail
                .builder()
                .id(hotelIntegrationResponse.getId())
                .cityName(hotelIntegrationResponse.getCityName())
                .rooms(getRooms(hotelIntegrationResponse, quotation))
                .build();
    }

    private List<Room> getRooms(HotelIntegrationResponse hotelIntegrationResponse, final Quotation quotation) {
        return hotelIntegrationResponse
                .getRooms()
                .stream()
                .map(hotel -> Room
                        .builder()
                        .checkin(quotation.getCheckin())
                        .checkout(quotation.getCheckout())
                        .numberAdult(quotation.getNumberAdult())
                        .numberChild(quotation.getNumberChild())
                        .categoryName(hotel.getCategoryName())
                        .roomID(hotel.getRoomID())
                        .price(getPrice(hotel.getPrice()))
                        .build())
                .collect(Collectors.toList());
    }

    private Price getPrice(PrinceIntegrationResponse hotel) {
        return Price
                .builder()
                .adult(hotel.getAdult())
                .child(hotel.getChild())
                .build();
    }
}
