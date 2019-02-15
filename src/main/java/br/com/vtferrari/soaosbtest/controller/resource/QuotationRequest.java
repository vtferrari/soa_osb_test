package br.com.vtferrari.soaosbtest.controller.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class QuotationRequest {
    @NotNull
    private Long cityCode;
    @NotNull
    private LocalDate checkin;
    @NotNull
    private LocalDate checkout;
    @NotNull
    @Min(0)
    private Integer numberAdult;
    @NotNull
    @Min(0)
    private Integer numberChild;
}
