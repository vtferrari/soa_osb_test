package br.com.vtferrari.soaosbtest.controller.converter;

import br.com.vtferrari.soaosbtest.controller.resource.QuotationRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class QuotationRequestConverterTest {
    private QuotationRequestConverter quotationRequestConverter;

    @Before
    public void setup() {
        quotationRequestConverter = new QuotationRequestConverter();
    }

    @Test
    public void testShouldConvertQuotationDetailsInQuotationDetailResponse() {

        final var spec =
                QuotationRequest
                        .builder()
                        .cityCode(123L)
                        .numberAdult(1)
                        .numberChild(2)
                        .checkin(LocalDate.of(2010, 1, 1))
                        .checkout(LocalDate.of(2010, 1, 3))
                        .build();

        final var result = quotationRequestConverter.convert(spec).block();

        assertEquals(spec.getCityCode(), result.getCityCode());
        assertEquals(spec.getCheckin(), result.getCheckin());
        assertEquals(spec.getCheckout(), result.getCheckout());
        assertEquals(spec.getNumberAdult(), result.getNumberAdult());
        assertEquals(spec.getNumberChild(), result.getNumberChild());
    }

}