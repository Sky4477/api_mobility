package sig.agrogeomarket.app.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.geolatte.geom.crs.CoordinateReferenceSystem;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.GeometryFactory;
import sig.agrogeomarket.app.Model.Utils.CaracteristiquesGeometriques;
import sig.agrogeomarket.app.Model.Utils.TypeTerrain;

@Getter @NoArgsConstructor
@Setter @AllArgsConstructor
@Entity
@Table(name = "terre_agricole")
public class TerrainAgricole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nom;
    private double superficie;
    private boolean possede;
    private String CRS;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeTerrain typeTerrain;
    @Embedded
    private CaracteristiquesGeometriques caracteristiquesGeometriques;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proprietaire_id")
    @JsonBackReference
    private Proprietaire proprietaire;
    @Lob
    private byte[] imageRaster;
    @Column(name="other")
    private String other;

    @PrePersist
    public void prePersist() {
        this.possede = false;
    }

}