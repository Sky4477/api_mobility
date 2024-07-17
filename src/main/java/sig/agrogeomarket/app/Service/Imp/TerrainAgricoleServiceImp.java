package sig.agrogeomarket.app.Service.Imp;

import lombok.AllArgsConstructor;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sig.agrogeomarket.app.Model.TerrainAgricole;
import sig.agrogeomarket.app.Model.Utils.CaracteristiquesGeometriques;
import sig.agrogeomarket.app.Model.Utils.FeatureCollectionInfo;
import sig.agrogeomarket.app.Repository.TerrainAgricoleRepository;
import sig.agrogeomarket.app.Service.Interface.TerrainAgricoleService;
import sig.agrogeomarket.app.Service.Traitement.TraitementESRI;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class TerrainAgricoleServiceImp implements TerrainAgricoleService {

    private final TerrainAgricoleRepository repo;
    private final TraitementESRI traitementESRI;

    @Override

    public TerrainAgricole create(TerrainAgricole terrainAgricole) {
        return this.repo.save(terrainAgricole);
    }

    @Override
    public TerrainAgricole createFromESRIFile(MultipartFile file) {
        try {
            FeatureCollectionInfo collectionInfo = traitementESRI.readEsiFile(file);

            CaracteristiquesGeometriques caracteristiquesGeometriques = new CaracteristiquesGeometriques();
            if (collectionInfo != null) {
                TerrainAgricole terrain = new TerrainAgricole();
                SimpleFeatureCollection collection = collectionInfo.getFeatures();
                SimpleFeatureIterator iterator = collection.features();

                while (iterator.hasNext()) {
                    SimpleFeature feature = iterator.next();
                    Geometry geom = (Geometry) feature.getDefaultGeometry();
                    // Vérifier le type de géométrie
                    if (geom instanceof Polygon) {
                        caracteristiquesGeometriques.setEmplacementPolygone((Polygon) geom);
                        caracteristiquesGeometriques.setEmplacementPoint(geom.getCentroid());
                    } else if (geom instanceof MultiPolygon) {
                        // Pour les MultiPolygones, traiter chaque polygone individuellement
                        MultiPolygon multiPolygon = (MultiPolygon) geom;
                        for (int i = 0; i < multiPolygon.getNumGeometries(); i++) {
                            Polygon polygon = (Polygon) multiPolygon.getGeometryN(i);
                            caracteristiquesGeometriques.setEmplacementPolygone(polygon);
                            caracteristiquesGeometriques.setEmplacementPoint(polygon.getCentroid());
                        }
                    } else {
                        // Si ce n'est ni un Polygon ni un MultiPolygon (ce cas est peu probable avec des données ESRI valides)
                        caracteristiquesGeometriques.setEmplacementPoint(geom.getCentroid());
                    }
                    // Attribution des attributs à TerrainAgricole
                    terrain.setNom((String) feature.getAttribute("nom"));
                    terrain.setSuperficie(geom.getArea());
                    terrain.setCaracteristiquesGeometriques(caracteristiquesGeometriques);
                    if (collectionInfo.getCrs() != null) {
                        CoordinateReferenceSystem crs = collectionInfo.getCrs();
                        terrain.setCRS(crs.toString());
                    }else {
                        terrain.setCRS("EPSG:4315");
                    }
                    // Affichage des attributs (optionnel)
                    Collection<Property> properties = feature.getProperties();
                    for (Property property : properties) {
                       // System.out.println(property.getName() + ": " + property.getValue());
                    }
                }
                iterator.close();
                return repo.save(terrain);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Retourner null si une erreur survient ou si aucun objet TerrainAgricole n'est créé
        return null;
    }

    @Override

    public TerrainAgricole update(TerrainAgricole terrainAgricole) {
        // Implémentez la logique de mise à jour du terrain agricole si nécessaire
        return null;
    }

    @Override

    public void delete(Long id) {
        // Implémentez la logique de suppression du terrain agricole si nécessaire
    }

    @Override
    public TerrainAgricole findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<TerrainAgricole> findAll() {
        return repo.findAll();
    }

    @Override
    public List<List<TerrainAgricole>> isPossede() {
        // Implémentez la logique de recherche des terrains possédés si nécessaire
        return null;
    }
}
