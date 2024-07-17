package sig.agrogeomarket.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sig.agrogeomarket.app.Model.Proprietaire;

public interface ProprietaireRepository extends JpaRepository<Proprietaire, Long> {
}