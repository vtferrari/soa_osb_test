package br.com.vtferrari.soaosbtest.service.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void testShouldReturnTotalFromQuotationWithStay0Days(){
        var room = Room
                .builder()
                .numberChild(0)
                .numberAdult(1)
                .checkout(LocalDate.of(2010,01,01))
                .checkin(LocalDate.of(2010,01,01))
                .price(Price.builder().adult(BigDecimal.ONE).child(BigDecimal.ZERO).build())
                .build();

        assertEquals(BigDecimal.ZERO,room.getTotalPrice());
    }

    @Test
    public void testShouldReturnTotalFromQuotationWithStay10Days(){
        var room = Room
                .builder()
                .numberChild(0)
                .numberAdult(1)
                .checkin(LocalDate.of(2010,1,1))
                .checkout(LocalDate.of(2010,1,10))
                .price(Price.builder().adult(BigDecimal.ONE).child(BigDecimal.ZERO).build())
                .build();

        assertEquals(BigDecimal.valueOf(13),room.getTotalPrice());
    }
    @Test
    public void testShouldReturnTotalFromQuotationWithStay10DaysAnd2Adults(){
        var room = Room
                .builder()
                .numberChild(0)
                .numberAdult(2)
                .checkin(LocalDate.of(2010,1,1))
                .checkout(LocalDate.of(2010,1,10))
                .price(Price.builder().adult(BigDecimal.valueOf(500)).child(BigDecimal.ZERO).build())
                .build();

        assertEquals(BigDecimal.valueOf(6429),room.getTotalPrice());
    }

}