package br.com.vtferrari.soaosbtest.controller.converter;

import br.com.vtferrari.soaosbtest.service.model.Price;
import br.com.vtferrari.soaosbtest.service.model.QuotationDetail;
import br.com.vtferrari.soaosbtest.service.model.Room;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuotationDetailConverterTest {
    private QuotationDetailConverter quotationDetailConverter;

    @Before
    public void setup() {
        quotationDetailConverter = new QuotationDetailConverter();
    }

    @Test
    public void testShouldConvertQuotationDetailsInQuotationDetailResponse() {

        final var price =
                Price
                        .builder()
                        .child(BigDecimal.ONE)
                        .adult(BigDecimal.TEN)
                        .build();
        final var room =
                Room
                        .builder()
                        .roomID(2L)
                        .numberAdult(1)
                        .numberChild(2)
                        .checkin(LocalDate.of(2010, 1, 1))
                        .checkout(LocalDate.of(2010, 1, 3))
                        .price(price)
                        .build();
        final var spec =
                QuotationDetail
                        .builder()
                        .id(1L)
                        .cityName("Santo André")
                        .rooms(List.of(room))
                        .build();

        final var result = quotationDetailConverter.convert(spec).block();

        assertEquals(spec.getCityName(), result.getCityName());
        assertEquals(spec.getId(), result.getId());
        assertEquals(spec.getRooms().get(0).getRoomID(), result.getRooms().get(0).getRoomID());
        assertEquals(spec.getRooms().get(0).getTotalPrice(), result.getRooms().get(0).getTotalPrice());
        assertEquals(spec.getRooms().get(0).getCategoryName(), result.getRooms().get(0).getCategoryName());
        assertEquals(spec.getRooms().get(0).getPrice().getAdult(), result.getRooms().get(0).getPriceDetail().getPricePerDayAdult());
        assertEquals(spec.getRooms().get(0).getPrice().getChild(), result.getRooms().get(0).getPriceDetail().getPricePerDayChild());
    }

}