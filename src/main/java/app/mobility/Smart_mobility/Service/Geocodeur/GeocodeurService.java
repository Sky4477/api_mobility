package app.mobility.Smart_mobility.Service.Geocodeur;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeocodeurService {
    @Value("${nominatim.url}")
    private String nominatimUrl; // URL de l'API Nominatim, injectée via application.properties
    private final RestTemplate restTemplate;
    public GeocodeurService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String getAddressFromCoordinates(String latitude, String longitude) {
        String address = "";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(nominatimUrl + "/reverse")
                .queryParam("format", "json")
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("zoom", 18); // Zoom pour précision
        // Effectuer la requête GET vers Nominatim
        try {
            address = restTemplate.getForObject(builder.toUriString(), String.class);
        } catch (Exception e) {
            // Gérer les erreurs de requête
            e.printStackTrace();
        }
        return address;
    }
    // public String reverse(String latitude, String longitude) {
    //     String address = "";
    //     // Construction de l'URL pour la requête vers Nominatim
    //     UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(nominatimUrl + "/reverse")
    //             .queryParam("format", "json")
    //             .queryParam("lat", latitude)
    //             .queryParam("lon", longitude)
    //             .queryParam("zoom", 18); // Zoom pour précision
    //     // Effectuer la requête GET vers Nominatim
    //     try {
    //         address = restTemplate.getForObject(builder.toUriString(), String.class);
    //     } catch (Exception e) {
    //         // Gérer les erreurs de requête
    //         e.printStackTrace();
    //     }
    //     return address;
    // }
}
