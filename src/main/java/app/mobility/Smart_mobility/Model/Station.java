package app.mobility.Smart_mobility.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Getter
@Setter
@Entity
@Table(name = "station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "Type")
    private String type;
    @Column(name = "name")
    private String name;
    @Column(name = "LigneID")
    private String ligneID;
    @Column(name = "StationID")
    private String stationID;
    @Column(name = "geom",columnDefinition = "geometry(Point, 4326)")
    private Point geometry;
    @Column(name ="latitude")
    private String latitude;
    @Column(name ="longitude")
    private String longitude;

}