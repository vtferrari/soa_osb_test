package br.com.vtferrari.soaosbtest.integration;

import br.com.vtferrari.soaosbtest.integration.resource.HotelIntegrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "hotel", url = "https://cvcbackendhotel.herokuapp.com/hotels/avail")
public interface HotelIntegration {

    @GetMapping("/{cityCode}")
    List<HotelIntegrationResponse> listHotelByCity(@PathVariable("cityCode") Long cityCode);
}
