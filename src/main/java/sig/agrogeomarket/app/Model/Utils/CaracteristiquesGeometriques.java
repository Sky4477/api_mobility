package sig.agrogeomarket.app.Model.Utils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeocentricCRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class CaracteristiquesGeometriques {

    @Column(name = "emplacement_point",columnDefinition="geometry(POINT,32632)")
    @JsonBackReference
    private Point emplacementPoint;


    
    @Column(name = "emplacement_polygone",columnDefinition="geometry(POLYGON,4326)")
    @JsonBackReference
    private Polygon emplacementPolygone;

}
