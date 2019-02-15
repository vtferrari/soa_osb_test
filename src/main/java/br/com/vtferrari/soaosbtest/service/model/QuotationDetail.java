package br.com.vtferrari.soaosbtest.service.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuotationDetail {
    private Long id;
    private String cityName;
    private List<Room> rooms;
}
