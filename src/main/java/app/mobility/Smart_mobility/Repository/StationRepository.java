package app.mobility.Smart_mobility.Repository;

import app.mobility.Smart_mobility.Model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    // Recherche une station par son nom
    Station findByName(String name);

    // Retourne toutes les stations
    List<Station> findAll();

    List<Station> findByNameLike(String name);

}