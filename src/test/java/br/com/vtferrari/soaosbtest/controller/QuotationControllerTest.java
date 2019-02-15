package br.com.vtferrari.soaosbtest.controller;

import br.com.vtferrari.soaosbtest.controller.converter.QuotationDetailConverter;
import br.com.vtferrari.soaosbtest.controller.converter.QuotationRequestConverter;
import br.com.vtferrari.soaosbtest.controller.resource.QuotationRequest;
import br.com.vtferrari.soaosbtest.service.QuotationService;
import br.com.vtferrari.soaosbtest.service.model.Price;
import br.com.vtferrari.soaosbtest.service.model.Quotation;
import br.com.vtferrari.soaosbtest.service.model.QuotationDetail;
import br.com.vtferrari.soaosbtest.service.model.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuotationControllerTest {

    @InjectMocks
    private QuotationController quotationController;
    @Mock
    private QuotationService quotationService;
    @Mock
    private QuotationRequestConverter quotationRequestConverter;
    @Mock
    private QuotationDetailConverter detailConverter;

    @Test
    public void findAllQuotation() throws Exception {
        final var spec = QuotationRequest
                .builder()
                .cityCode(12L)
                .checkin(LocalDate.of(2010, 1, 1))
                .checkout(LocalDate.of(2010, 1, 1))
                .numberAdult(2)
                .numberChild(1)
                .build();
        when(quotationRequestConverter.convert(any(QuotationRequest.class)))
                .thenCallRealMethod();


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
        final var specQuotationDetail =
                QuotationDetail
                        .builder()
                        .id(1L)
                        .cityName("Santo André")
                        .rooms(List.of(room))
                        .build();
        when(quotationService.quote(any(Quotation.class)))
                .thenReturn(Flux.just(specQuotationDetail));
        when(detailConverter.convert(any(QuotationDetail.class)))
                .thenCallRealMethod();

        final var quotationDetailResponse =
                quotationController.quotation(Mono.just(spec)).blockLast();

        assertNotNull(quotationDetailResponse);

        verify(quotationRequestConverter, times(1)).convert(any(QuotationRequest.class));
        verify(quotationService, times(1)).quote(any(Quotation.class));
        verify(detailConverter, times(1)).convert(any(QuotationDetail.class));
    }

    @Test
    public void findAllQuotationByHotelId() throws Exception {
        final var spec = QuotationRequest
                .builder()
                .cityCode(12L)
                .checkin(LocalDate.of(2010, 1, 1))
                .checkout(LocalDate.of(2010, 1, 1))
                .numberAdult(2)
                .numberChild(1)
                .build();
        when(quotationRequestConverter.convert(any(QuotationRequest.class)))
                .thenCallRealMethod();


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
        final var specQuotationDetail =
                QuotationDetail
                        .builder()
                        .id(1L)
                        .cityName("Santo André")
                        .rooms(List.of(room))
                        .build();
        when(quotationService.quote(anyLong(), any(Quotation.class)))
                .thenReturn(Mono.just(specQuotationDetail));
        when(detailConverter.convert(any(QuotationDetail.class)))
                .thenCallRealMethod();

        final var quotationDetailResponse =
                quotationController.quotation(1L, Mono.just(spec)).block();

        assertNotNull(quotationDetailResponse);

        verify(quotationRequestConverter, times(1)).convert(any(QuotationRequest.class));
        verify(quotationService, times(1)).quote(anyLong(), any(Quotation.class));
        verify(detailConverter, times(1)).convert(any(QuotationDetail.class));
    }
}