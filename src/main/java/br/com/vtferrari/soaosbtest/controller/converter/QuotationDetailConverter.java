package br.com.vtferrari.soaosbtest.controller.converter;

import br.com.vtferrari.soaosbtest.controller.resource.PriceDetailResponse;
import br.com.vtferrari.soaosbtest.controller.resource.QuotationDetailResponse;
import br.com.vtferrari.soaosbtest.controller.resource.RoomResponse;
import br.com.vtferrari.soaosbtest.service.model.Price;
import br.com.vtferrari.soaosbtest.service.model.QuotationDetail;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuotationDetailConverter {

    public Mono<QuotationDetailResponse> convert(final QuotationDetail quotationDetail) {
        return Mono.just(QuotationDetailResponse.builder()
                .id(quotationDetail.getId())
                .cityName(quotationDetail.getCityName())
                .rooms(getRooms(quotationDetail))
                .build());
    }

    private List<RoomResponse> getRooms(QuotationDetail quotationDetail) {
        return quotationDetail
                .getRooms()
                .stream()
                .map(room -> RoomResponse
                        .builder()
                        .roomID(room.getRoomID())
                        .priceDetail(getPrice(room.getPrice()))
                        .categoryName(room.getCategoryName())
                        .totalPrice(room.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }

    private PriceDetailResponse getPrice(Price price) {
        return PriceDetailResponse
                .builder()
                .pricePerDayAdult(price.getAdult())
                .pricePerDayChild(price.getChild())
                .build();
    }
}
