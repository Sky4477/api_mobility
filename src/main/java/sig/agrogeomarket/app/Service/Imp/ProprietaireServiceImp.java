package sig.agrogeomarket.app.Service.Imp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sig.agrogeomarket.app.Model.Proprietaire;
import sig.agrogeomarket.app.Model.TerrainAgricole;
import sig.agrogeomarket.app.Repository.ProprietaireRepository;
import sig.agrogeomarket.app.Service.Interface.ProprietaireService;

import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
@AllArgsConstructor
public class ProprietaireServiceImp implements ProprietaireService {
    private final ProprietaireRepository repo;
    
    @Override
    public Proprietaire create(Proprietaire proprietaire) {
        return this.repo.save(proprietaire);
    }

    @Override
    public Proprietaire update(Proprietaire proprietaire) {
        Proprietaire newproprietaire = this.repo.findById(proprietaire.getId()).get();
        newproprietaire.setNom(proprietaire.getNom());
        newproprietaire.setAdresse(proprietaire.getAdresse());
        newproprietaire.setTerrainsAgricoles(proprietaire.getTerrainsAgricoles());
        return this.repo.save(newproprietaire);
    }
    @Override
    public void delete(Long id) {
        Proprietaire proprietaire = this.repo.findById(id).get();
        this.repo.delete(proprietaire);
    }
    @Override
    public Proprietaire findById(Long id) {
        return this.repo.findById(id).get();
    }
    @Override
    public List<Proprietaire> findAll() {
        return this.repo.findAll();
    }
    @Override
    public void addTerrainAgricole(Proprietaire proprietaire, TerrainAgricole terrainAgricole) {
        proprietaire.getTerrainsAgricoles().add(terrainAgricole);
    }
    @Override
    public void removeTerrainAgricole(Proprietaire proprietaire, TerrainAgricole terrainAgricole) {
        proprietaire.getTerrainsAgricoles().remove(terrainAgricole);
    }
    @Override
    public List<TerrainAgricole> isPossede(Proprietaire proprietaire) {
        // Rechercher le propriétaire dans le référentiel
        Optional<Proprietaire> optionalProprietaire = this.repo.findById(proprietaire.getId());
        if (optionalProprietaire.isPresent()) {
            Collection<TerrainAgricole> terrainsAgricoles = optionalProprietaire.get().getTerrainsAgricoles();
            return new ArrayList<>(terrainsAgricoles);
        } else {

            return Collections.emptyList();
        }
    }

    @Override
    public void updatePossede(Proprietaire proprietaire, TerrainAgricole terrainAgricole) {
        terrainAgricole.setPossede(true);
        this.addTerrainAgricole(proprietaire, terrainAgricole);
    }
}
