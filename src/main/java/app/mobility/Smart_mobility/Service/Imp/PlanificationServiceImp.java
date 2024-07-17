package app.mobility.Smart_mobility.Service.Imp;

import app.mobility.Smart_mobility.Model.HorraireData;
import app.mobility.Smart_mobility.Model.Station;
import app.mobility.Smart_mobility.Service.Interf.PlanificationService;
import app.mobility.Smart_mobility.Service.Interf.StationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlanificationServiceImp implements PlanificationService {
    @Override
    public List<HorraireData> getPlanification() {
        return null;
    }

    @Override
    public List<HorraireData> getAllHorraireData() {
        return null;
    }

    @Override
    public List<HorraireData> getHorraireDataByStation(String stationName) {
        return null;
    }

    @Override
    public List<Station> getEstimationTimeFormUserLocation(String latitude, String longitude) {
        StationService stationService = new StationServiceImpl();
        List<Station> stations = new LogiquePlanificationService().getStationsWithinRadius(latitude, longitude, stationService);
        List<Station> stationsWithEstimationTime = new ArrayList<>();
        for (Station station : stations) {
            stationsWithEstimationTime.add(station);
        }

        return null;
    }
}

@AllArgsConstructor
class GetHoraires{
    public List<HorraireData> getArrianamoData() {
        return null;
    }
}

@AllArgsConstructor
@Transactional

class LogiquePlanificationService{

    List<Station> getStationsWithinRadius(String latitude, String longitude, StationService service) {
        // Assuming you have a method to calculate distance between coordinates
        List<Station> allStations = service.getAllStations();
        List<Station> nearbyStations = new ArrayList<>();
        for (Station station : allStations) {
            double distance = calculateDistance(latitude, longitude, station.getLatitude(), station.getLongitude());
            if (distance <= 5) { // 5 km radius
                nearbyStations.add(station);
            }
        }
        return nearbyStations;
    }
    private double calculateDistance(String userLatitude, String userLongitude, String stationLatitude, String stationLongitude) {
        // Convert latitude and longitude strings to double values
        double userLat = Double.parseDouble(userLatitude);
        double userLon = Double.parseDouble(userLongitude);
        double stationLat = Double.parseDouble(stationLatitude);
        double stationLon = Double.parseDouble(stationLongitude);

        // Implement the Haversine formula for distance calculation
        final int R = 6371; // Radius of the Earth in kilometers

        double latDistance = Math.toRadians(stationLat - userLat);
        double lonDistance = Math.toRadians(stationLon - userLon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(stationLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }

private String calculateEstimatedArrivalTime(HorraireData horraireData) {
    LocalTime currentTime = LocalTime.now();
    int currentTimeInMinutes = currentTime.getHour() * 60 + currentTime.getMinute();
    int frequency = horraireData.getFrequence();
    LocalTime firstDeparture = LocalTime.parse(horraireData.getPremierDepartStation(), DateTimeFormatter.ofPattern("HH:mm"));
    int firstDepartureInMinutes = firstDeparture.getHour() * 60 + firstDeparture.getMinute();
    LocalTime lastDeparture = LocalTime.parse(horraireData.getDernierDepartStation(), DateTimeFormatter.ofPattern("HH:mm"));
    int lastDepartureInMinutes = lastDeparture.getHour() * 60 + lastDeparture.getMinute();

    int estimatedArrivalInMinutes;
    if (currentTimeInMinutes > lastDepartureInMinutes + frequency) {
        estimatedArrivalInMinutes = firstDepartureInMinutes;
    } else if (currentTimeInMinutes < firstDepartureInMinutes) {
        estimatedArrivalInMinutes = firstDepartureInMinutes;
    } else {
        estimatedArrivalInMinutes = currentTimeInMinutes + frequency;
        if (estimatedArrivalInMinutes > lastDepartureInMinutes + frequency) {
            estimatedArrivalInMinutes = firstDepartureInMinutes;
        }
    }
    LocalTime estimatedArrivalTime = LocalTime.of((estimatedArrivalInMinutes / 60), (estimatedArrivalInMinutes % 60));
    return estimatedArrivalTime.format(DateTimeFormatter.ofPattern("HH:mm"));
}

}
