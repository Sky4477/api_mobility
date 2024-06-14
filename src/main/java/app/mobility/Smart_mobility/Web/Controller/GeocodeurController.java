package app.mobility.Smart_mobility.Web.Controller;

import app.mobility.Smart_mobility.Service.Geocodeur.GeocodeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class GeocodeurController {
    private final GeocodeurService geocodeurService;
    @Autowired
    public GeocodeurController(GeocodeurService geocodeurService) {
        this.geocodeurService = geocodeurService;
    }

    @GetMapping("/geocode/reverse")
    public String reverseGeocode(@RequestParam String lat, @RequestParam String lon) {
        return geocodeurService.getAddressFromCoordinates(lat, lon);
    }
}
