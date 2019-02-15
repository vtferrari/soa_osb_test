package br.com.vtferrari.soaosbtest.service.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;

@Builder
@Getter
public class Room {
    public static final BigDecimal TAX = BigDecimal.valueOf(0.70);

    private Long roomID;
    private String categoryName;
    private LocalDate checkin;
    private LocalDate checkout;
    private Integer numberAdult;
    private Integer numberChild;
    private Price price;

    public BigDecimal getTotalPrice() {
        return getTotalStayPricePerAdult().add(getTotalStayPricePerChild());
    }

    private BigDecimal getTotalStayPricePerAdult() {
        final BigDecimal stay = BigDecimal.valueOf(getStayInDays());
        return price.getAdult().multiply(stay).divide(TAX, RoundingMode.HALF_UP);
    }

    private BigDecimal getTotalStayPricePerChild() {
        final BigDecimal stay = BigDecimal.valueOf(getStayInDays());
        return price.getChild().multiply(stay).divide(TAX, RoundingMode.HALF_UP);
    }

    private Long getStayInDays() {
        return Duration.between(checkin.atStartOfDay(), checkout.atStartOfDay()).toDays();
    }
}
