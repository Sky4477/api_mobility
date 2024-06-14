package app.mobility.Smart_mobility.Repository;

import app.mobility.Smart_mobility.Model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    @Override
    void deleteById(Long aLong);

}