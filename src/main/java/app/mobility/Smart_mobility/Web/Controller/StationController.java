package app.mobility.Smart_mobility.Web.Controller;

import app.mobility.Smart_mobility.Model.Station;
import app.mobility.Smart_mobility.Service.Interf.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    // Endpoint pour créer une nouvelle station
    @PostMapping
    public ResponseEntity<Station> createStation(@RequestBody Station station) {
        Station createdStation = stationService.createStation(station);
        return new ResponseEntity<>(createdStation, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer une station par son nom
    @GetMapping("/{name}")
    public ResponseEntity<Station> getStationByName(@PathVariable String name) {
        Station station = stationService.getStationByName(name);
        if (station != null) {
            return new ResponseEntity<>(station, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{like}")
    public ResponseEntity<Station> getStationByNameLike(@PathVariable String like) {
        List<Station> station = stationService.findByNameLike(like);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint pour récupérer toutes les stations
    @GetMapping
    public ResponseEntity<List<Station>> getAllStations() {
        List<Station> stations = stationService.getAllStations();
        return new ResponseEntity<>(stations, HttpStatus.OK);
    }


    // Endpoint pour mettre à jour une station
    @PutMapping("/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable Long id, @RequestBody Station updatedStation) {
        Station station = stationService.updateStation(id, updatedStation);
        if (station != null) {
            return new ResponseEntity<>(station, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour supprimer une station
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        stationService.deleteStation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
