package sig.agrogeomarket.app.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "proprietaire")
public class Proprietaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String adresse;

    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL,orphanRemoval = true)
    @Column(name = "terrainsAgricoles",nullable = false)
    private Collection<TerrainAgricole> terrainsAgricoles=new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.terrainsAgricoles=new ArrayList<>();
    }
}