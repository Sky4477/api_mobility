package sig.agrogeomarket.app.Service.Interface;

import sig.agrogeomarket.app.Model.Proprietaire;
import sig.agrogeomarket.app.Model.TerrainAgricole;

import java.util.List;

public interface ProprietaireService {
    Proprietaire create(Proprietaire proprietaire);
    Proprietaire update(Proprietaire proprietaire);
    void delete(Long id);
    Proprietaire findById(Long id);
    List<Proprietaire> findAll();
    void addTerrainAgricole(Proprietaire proprietaire, TerrainAgricole terrainAgricole);
    void removeTerrainAgricole(Proprietaire proprietaire, TerrainAgricole terrainAgricole);
    List<TerrainAgricole> isPossede(Proprietaire proprietaire);
    void updatePossede(Proprietaire proprietaire, TerrainAgricole terrainAgricole);
}
