package app.mobility.Smart_mobility.Service.Interf;

import app.mobility.Smart_mobility.Model.Station;

import java.util.List;

public interface StationService {
    Station createStation(Station station);

    Station getStationByName(String name);

    List<Station> getAllStations();

    Station updateStation(Long id, Station updatedStation);

    void deleteStation(Long id);

    List<Station> getAllStationsFromFiles(String type, String filePaths);
    List<Station> findByNameLike(String name);
}
