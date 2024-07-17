package app.mobility.Smart_mobility.Service.Interf;

import app.mobility.Smart_mobility.Model.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertService {
    List<Alert> getAllAlerts();

    Optional<Alert> getAlertById(Long id);

    Alert createAlert(Alert alert);

    Alert updateAlert(Long id, Alert alert);

    void deleteAlert(Long id);

}
