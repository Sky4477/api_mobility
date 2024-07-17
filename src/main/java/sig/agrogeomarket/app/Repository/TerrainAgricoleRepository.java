package sig.agrogeomarket.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sig.agrogeomarket.app.Model.TerrainAgricole;
import sig.agrogeomarket.app.Model.Utils.TypeTerrain;

import java.util.List;

public interface TerrainAgricoleRepository extends JpaRepository<TerrainAgricole, Long> {


    List<TerrainAgricole> findByTypeTerrain(TypeTerrain typeTerrain);
    TerrainAgricole findByNom(String nom);

    List<TerrainAgricole> findByPossede(boolean possede);

}