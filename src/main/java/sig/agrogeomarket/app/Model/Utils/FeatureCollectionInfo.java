package sig.agrogeomarket.app.Model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeatureCollectionInfo {
    private String fileName;
    private int numberOfFeatures;
    private String geometryType;
    private CoordinateReferenceSystem crs;
    private SimpleFeatureCollection features;
    private SimpleFeatureType featureType;

}
