package br.com.vtferrari.soaosbtest.service;

import br.com.vtferrari.soaosbtest.integration.HotelIntegration;
import br.com.vtferrari.soaosbtest.integration.converter.HotelIntegrationResponseAdapter;
import br.com.vtferrari.soaosbtest.integration.resource.HotelIntegrationResponse;
import br.com.vtferrari.soaosbtest.service.model.Quotation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuotationServiceTest {
    @InjectMocks
    private QuotationService quotationService;
    @Mock
    private HotelIntegration hotelIntegration;
    @Mock
    private HotelIntegrationResponseAdapter hotelIntegrationResponseAdapter;

    @Test
    public void testShouldReturnNullWhenEmptyQuotation() {

        final var quotation = Quotation.builder().build();
        final var quotationDetail = quotationService.quote(quotation).blockLast();
        assertNull(quotationDetail);
        verify(hotelIntegration, times(0)).listHotelByCity(anyLong());
        verify(hotelIntegrationResponseAdapter, times(0)).merge(any(HotelIntegrationResponse.class), any(Quotation.class));

    }

    @Test
    public void testShouldReturnListWhenNotEmptyQuotation() {

        when(hotelIntegration.listHotelByCity(anyLong()))
                .thenReturn(List.of(HotelIntegrationResponse.builder().rooms(List.of()).build()));
        when(hotelIntegrationResponseAdapter.merge(any(HotelIntegrationResponse.class), any(Quotation.class)))
                .thenCallRealMethod();

        final var quotation = Quotation.builder().cityCode(1234L).build();
        final var quotationDetail = quotationService.quote(quotation).blockLast();
        assertNotNull(quotationDetail);
        verify(hotelIntegration, times(1)).listHotelByCity(anyLong());
        verify(hotelIntegrationResponseAdapter, times(1)).merge(any(HotelIntegrationResponse.class), any(Quotation.class));
    }

    @Test
    public void testShouldReturnNullWhenEmptyHotelIdQuotation() {
        when(hotelIntegration.getHotelById(anyLong()))
                .thenReturn(List.of(HotelIntegrationResponse.builder().rooms(List.of()).build()));
        when(hotelIntegrationResponseAdapter.merge(any(HotelIntegrationResponse.class), any(Quotation.class)))
                .thenCallRealMethod();

        final var quotation = Quotation.builder().build();
        final var quotationDetail = quotationService.quote(1L, quotation).block();
        assertNotNull(quotationDetail);
        verify(hotelIntegration, times(1)).getHotelById(anyLong());
        verify(hotelIntegrationResponseAdapter, times(1)).merge(any(HotelIntegrationResponse.class), any(Quotation.class));

    }

    @Test
    public void testShouldReturnListWhenNotEmptyHotelIdQuotation() {

        when(hotelIntegration.getHotelById(anyLong()))
                .thenReturn(List.of(HotelIntegrationResponse.builder().rooms(List.of()).build()));
        when(hotelIntegrationResponseAdapter.merge(any(HotelIntegrationResponse.class), any(Quotation.class)))
                .thenCallRealMethod();

        final var quotation = Quotation.builder().cityCode(1234L).build();
        final var quotationDetail = quotationService.quote(1L, quotation).block();
        assertNotNull(quotationDetail);
        verify(hotelIntegration, times(1)).getHotelById(anyLong());
        verify(hotelIntegrationResponseAdapter, times(1)).merge(any(HotelIntegrationResponse.class), any(Quotation.class));

    }
}