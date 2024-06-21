package app.mobility.Smart_mobility.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "geo_file_addedby_user")
@NoArgsConstructor
@AllArgsConstructor
public class GeoFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String filename;
    private String fileType;

    @Lob
    private byte[] data;

}