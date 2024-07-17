package sig.agrogeomarket.app.Web.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sig.agrogeomarket.app.Service.Interface.GeocodingService;
import sig.agrogeomarket.app.Model.GeocodeResponse;

@RestController
@RequestMapping(value = "/search")
@AllArgsConstructor
public class GeocodingController {
    private final GeocodingService service;

    @GetMapping("/geocode")
    public GeocodeResponse geocode(@RequestParam String address) {
        return service.geocode(address);
    }

    @GetMapping("/reverse-geocode")
    public GeocodeResponse reverseGeocode(@RequestParam double lat, @RequestParam double lon) {
        return service.reverseGeocode(lat, lon);
    }

}
