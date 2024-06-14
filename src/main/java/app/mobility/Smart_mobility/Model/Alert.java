package app.mobility.Smart_mobility.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alert")
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String type;
    private String date;
    private String coordinates;
    private String commentaire;
    private String icon;
    private String rue;
    private String zone;
    private String ville;
    private String codePostal;


    @PrePersist
    public void prePersist() {

    }
}