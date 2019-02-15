package br.com.vtferrari.soaosbtest.integration;

import br.com.vtferrari.soaosbtest.integration.resource.HotelIntegrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "hotel", url = "${api.hotels}")
public interface HotelIntegration {

    @GetMapping("/avail/{cityCode}")
    List<HotelIntegrationResponse> listHotelByCity(@PathVariable("cityCode") Long cityCode);

    @GetMapping("/{hotelId}")
    List<HotelIntegrationResponse> getHotelById(@PathVariable("hotelId") Long hotelId);
}
