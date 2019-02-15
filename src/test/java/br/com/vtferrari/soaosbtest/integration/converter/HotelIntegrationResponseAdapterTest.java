package br.com.vtferrari.soaosbtest.integration.converter;

import br.com.vtferrari.soaosbtest.integration.resource.HotelIntegrationResponse;
import br.com.vtferrari.soaosbtest.integration.resource.PrinceIntegrationResponse;
import br.com.vtferrari.soaosbtest.integration.resource.RoomIntegrationResponse;
import br.com.vtferrari.soaosbtest.service.model.Quotation;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HotelIntegrationResponseAdapterTest {
    private HotelIntegrationResponseAdapter hotelIntegrationResponseAdapter;

    @Before
    public void setup() {
        hotelIntegrationResponseAdapter = new HotelIntegrationResponseAdapter();
    }

    @Test
    public void testShouldConvertQuotationDetailsInQuotationDetailResponse() {
        final var quotationSpec =
                Quotation
                        .builder()
                        .cityCode(123L)
                        .numberAdult(1)
                        .numberChild(2)
                        .checkin(LocalDate.of(2010, 1, 1))
                        .checkout(LocalDate.of(2010, 1, 3))
                        .build();

        final var price =
                PrinceIntegrationResponse
                        .builder()
                        .child(BigDecimal.ONE)
                        .adult(BigDecimal.TEN)
                        .build();
        final var room =
                RoomIntegrationResponse
                        .builder()
                        .roomID(2L)
                        .price(price)
                        .build();
        final var spec =
                HotelIntegrationResponse
                        .builder()
                        .id(1L)
                        .cityName("Santo André")
                        .rooms(List.of(room))
                        .build();

        final var result = hotelIntegrationResponseAdapter.merge(spec, quotationSpec);

        assertEquals(spec.getCityName(), result.getCityName());
        assertEquals(spec.getId(), result.getId());
        assertEquals(spec.getRooms().get(0).getRoomID(), result.getRooms().get(0).getRoomID());
        assertEquals(spec.getRooms().get(0).getCategoryName(), result.getRooms().get(0).getCategoryName());
        assertEquals(quotationSpec.getCheckin(), result.getRooms().get(0).getCheckin());
        assertEquals(quotationSpec.getCheckout(), result.getRooms().get(0).getCheckout());
        assertEquals(quotationSpec.getNumberAdult(), result.getRooms().get(0).getNumberAdult());
        assertEquals(quotationSpec.getNumberChild(), result.getRooms().get(0).getNumberChild());
        assertEquals(spec.getRooms().get(0).getPrice().getAdult(), result.getRooms().get(0).getPrice().getAdult());
        assertEquals(spec.getRooms().get(0).getPrice().getChild(), result.getRooms().get(0).getPrice().getChild());
    }
}