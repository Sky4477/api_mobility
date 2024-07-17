package app.mobility.Smart_mobility.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "horraire_data")
@AllArgsConstructor
@NoArgsConstructor
public class HorraireData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ligne")
    private String ligne;

    @Column(name = "destination")
    private String destination;

    @Column(name = "premier_depart_station")
    private String premierDepartStation;

    @Column(name = "dernier_depart_station")
    private String dernierDepartStation;

    @Column(name = "premier_depart_banlieue")
    private String premierDepartBanlieue;

    @Column(name = "dernier_depart_banlieue")
    private String dernierDepartBanlieue;

    @Column(name = "fréquence")
    private int frequence;


}