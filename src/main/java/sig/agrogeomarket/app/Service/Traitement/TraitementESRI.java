package sig.agrogeomarket.app.Service.Traitement;

import org.springframework.web.multipart.MultipartFile;
import sig.agrogeomarket.app.Model.Utils.FeatureCollectionInfo;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.DefaultFeatureCollection;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TraitementESRI {

    public FeatureCollectionInfo readEsiFile(MultipartFile multipartFile) throws IOException {
        DataStore dataStore = null;
        DefaultFeatureCollection collection = new DefaultFeatureCollection();
        Map<String, File> tempFiles = new HashMap<>();

        try {
            // Créer un fichier temporaire pour le fichier principal .shp
            File tempShpFile = File.createTempFile("temp", ".shp");
            multipartFile.transferTo(tempShpFile);
            tempFiles.put("shp", tempShpFile);

            // Créer les autres fichiers temporaires associés (.shx, .dbf, .cpg, .prj, .qix)
            tempFiles.put("shx", createTempFile(tempShpFile, ".shx"));
            tempFiles.put("dbf", createTempFile(tempShpFile, ".dbf"));
            tempFiles.put("cpg", createTempFile(tempShpFile, ".cpg"));
            tempFiles.put("prj", createTempFile(tempShpFile, ".prj"));
            tempFiles.put("qix", createTempFile(tempShpFile, ".qix"));

            // Connexion à la banque des données
            Map<String, Object> map = new HashMap<>();
            map.put("url", tempFiles.get("shp").toURI().toURL());
            dataStore = DataStoreFinder.getDataStore(map);

            if (dataStore != null) {
                String typeName = dataStore.getTypeNames()[0];
                SimpleFeatureSource featureSource = dataStore.getFeatureSource(typeName);
                collection = new DefaultFeatureCollection(typeName, featureSource.getSchema());
                collection.addAll(featureSource.getFeatures());

                CoordinateReferenceSystem crs = featureSource.getSchema().getCoordinateReferenceSystem();
                SimpleFeatureType featureType = featureSource.getSchema();
                int numberOfFeatures = collection.size();
                String geometryType = featureType.getGeometryDescriptor().getType().getName().toString();

                FeatureCollectionInfo collectionInfo = new FeatureCollectionInfo();
                collectionInfo.setFileName(multipartFile.getOriginalFilename());
                collectionInfo.setNumberOfFeatures(numberOfFeatures);
                collectionInfo.setGeometryType(geometryType);
                collectionInfo.setCrs(crs);
                collectionInfo.setFeatures(collection);
                collectionInfo.setFeatureType(featureType);
                return collectionInfo;
            } else {
                throw new IOException("Impossible de se connecter à la banque de données");
            }
        } catch (IOException e) {
            throw new IOException("Erreur lors de la lecture du fichier ESRI : " + e.getMessage());
        } finally {
            // Supprimer les fichiers temporaires après usage
            for (File tempFile : tempFiles.values()) {
                if (tempFile.exists()) {
                    tempFile.delete();
                }
            }
            if (dataStore != null) {
                dataStore.dispose();
            }
        }
    }

    // Méthode utilitaire pour créer un fichier temporaire associé
    private File createTempFile(File baseFile, String extension) throws IOException {
        File tempFile = new File(baseFile.getAbsolutePath().replace(".shp", extension));
        tempFile.createNewFile();
        return tempFile;
    }
}
