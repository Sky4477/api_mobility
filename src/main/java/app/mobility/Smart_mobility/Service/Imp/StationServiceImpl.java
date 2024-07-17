package app.mobility.Smart_mobility.Service.Imp;

import app.mobility.Smart_mobility.Model.Station;
import app.mobility.Smart_mobility.Repository.StationRepository;
import app.mobility.Smart_mobility.Service.Interf.StationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public Station getStationByName(String name) {
        return stationRepository.findByName(name);
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Station updateStation(Long id, Station updatedStation) {
        Optional<Station> optionalStation = stationRepository.findById(id);
        if (optionalStation.isPresent()) {
            updatedStation.setId(id);
            return stationRepository.save(updatedStation);
        }
        return null; // Ou gérer le cas où la station n'est pas trouvée
    }

    @Override
    public void deleteStation(Long id) {
        stationRepository.deleteById(id);
    }

    @Override
    public List<Station> getAllStationsFromFiles(String type, String filePaths) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Station> stations = new ArrayList<>();
        if (type.equals("TGM")) {
            stations.addAll(new GetStation().getTGMStation(filePaths));
        }else if (type.equals("Metro")) {
            stations.addAll(new GetStation().getMetroStation(filePaths));
        }else {
            stations.addAll(new GetStation().getBusStation(filePaths));
        }
       // stations.addAll(new GetStation().getBusStation(filePaths));
       return stationRepository.saveAll(stations);
    }


    @Override
    public List<Station> findByNameLike(String name) {
        return stationRepository.findByNameLike(name);
    }
}

@AllArgsConstructor
class GetStation{
    public  List<Station> getTGMStation(String  url) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Station> stations = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(url));
            JsonNode features = rootNode.get("features");
            if (features != null ) {
                for (JsonNode feature : features) {
                    JsonNode properties = feature.get("properties");
                    JsonNode geometry = feature.get("geometry");
                    JsonNode coordinates = geometry != null ? geometry.get("coordinates") : null;
                    if (properties != null && coordinates != null) {
                        Station station = new Station();
                        station.setLigneID("1");
                        station.setStationID(properties.get("id").asText());
                        station.setName(properties.get("station_fr").asText());
                        station.setType("TGM");
                        double longitude = coordinates.get(0).asDouble();
                        double latitude = coordinates.get(1).asDouble();
                        station.setLatitude(latitude+"");
                        station.setLongitude(longitude+"");
                        stations.add(station);
                        }

                    }
                }
            }catch (Exception e) {  throw new RuntimeException(e);}

        return stations;

    }
    public  List<Station> getMetroStation(String  url) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Station> stations = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(url));
            JsonNode features = rootNode.get("features");
            JsonNode type = rootNode.get("name");
            if (features != null && type != null) {
                for (JsonNode feature : features) {
                    JsonNode properties = feature.get("properties");
                    JsonNode geometry = feature.get("geometry");
                    JsonNode coordinates = geometry != null ? geometry.get("coordinates") : null;
                    if (properties != null && coordinates != null) {
                        Station station = new Station();
                        station.setLigneID(properties.get("N° de la ligne").asText());
                        station.setStationID(properties.get("N° de station").asText());
                        station.setName(properties.get("Non de station").asText());
                        station.setType("Metro");
                        double longitude = coordinates.get(0).asDouble();
                        double latitude = coordinates.get(1).asDouble();
                        station.setLatitude(latitude+"");
                        station.setLongitude(longitude+"");
                        stations.add(station);
                        }

                    }
                }
            }catch (Exception e) {  throw new RuntimeException(e);}

        return stations;
    }
    public List<Station> getBusStation(String  url) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Station> stations = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(url));
            JsonNode features = rootNode.get("features");
            JsonNode type = rootNode.get("name");

            if (features != null && type != null) {
                for (JsonNode feature : features) {
                    JsonNode properties = feature.get("properties");
                    JsonNode geometry = feature.get("geometry");
                    JsonNode coordinates = geometry != null ? geometry.get("coordinates") : null;

                    if (properties != null && coordinates != null) {
                        Station station = new Station();
                        station.setLigneID(properties.get("N° de la ligne").asText());
                        station.setStationID(properties.get("N° de station").asText());
                        station.setName(properties.get("Nom de station").asText());
                        station.setType("Bus");
                        double longitude = coordinates.get(0).asDouble();
                        double latitude = coordinates.get(1).asDouble();
                        station.setLatitude(latitude+"");
                        station.setLongitude(longitude+"");

                        stations.add(station);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stations;
    }

}
