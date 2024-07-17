package sig.agrogeomarket.app.Service.Imp;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sig.agrogeomarket.app.Model.GeocodeResponse;
import sig.agrogeomarket.app.Service.Interface.GeocodingService;

@Service
@Transactional
@AllArgsConstructor
public class GeocodingServiceImpl implements GeocodingService {
    @Value("${nominatim.url}")
    private String nominatimUrl;

    private final RestTemplate restTemplate;
    @Override
    public GeocodeResponse geocode(String address) {
        String url = String.format("%s/search?q=%s&format=json&addressdetails=1", nominatimUrl, address);
        return restTemplate.getForObject(url, GeocodeResponse[].class)[0];
    }

    @Override
    public GeocodeResponse reverseGeocode(double latitude, double longitude) {
        String url = String.format("%s/reverse?lat=%s&lon=%s&format=json&addressdetails=1", nominatimUrl, latitude, longitude);
        return restTemplate.getForObject(url, GeocodeResponse.class);
    }
}
