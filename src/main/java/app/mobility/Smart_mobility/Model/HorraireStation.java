package app.mobility.Smart_mobility.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "horraire_station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorraireStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sens;
    private String heure;

    public HorraireStation(String heure, String sens) {
        this.heure = heure;
        this.sens = sens;
    }
}
