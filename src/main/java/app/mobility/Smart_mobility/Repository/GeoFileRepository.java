package app.mobility.Smart_mobility.Repository;

import app.mobility.Smart_mobility.Model.GeoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeoFileRepository extends JpaRepository<GeoFile, Long> {
    Optional<GeoFile> findByFilename(String filename);
}