package sig.agrogeomarket.app.Service.Interface;

import sig.agrogeomarket.app.Model.GeocodeResponse;

public interface GeocodingService {
    GeocodeResponse geocode(String address);
    GeocodeResponse reverseGeocode(double latitude, double longitude);
}
