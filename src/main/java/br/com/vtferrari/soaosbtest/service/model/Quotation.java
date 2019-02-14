package br.com.vtferrari.soaosbtest.service.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Quotation {
    private Long cityCode;
    private LocalDate checkin;
    private LocalDate checkout;
    private Integer numberAdult;
    private Integer numberChild;
}