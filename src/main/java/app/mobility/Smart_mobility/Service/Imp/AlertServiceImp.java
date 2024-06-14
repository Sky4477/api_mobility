package app.mobility.Smart_mobility.Service.Imp;

import app.mobility.Smart_mobility.Model.Alert;
import app.mobility.Smart_mobility.Repository.AlertRepository;
import app.mobility.Smart_mobility.Service.Geocodeur.GeocodeurService;
import app.mobility.Smart_mobility.Service.Interf.AlertService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AlertServiceImp implements AlertService {

    @Autowired
    private AlertRepository alertRepository;
    private final GeocodeurService geocodeurService;

    @Override
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @Override
    public Optional<Alert> getAlertById(Long id) {
        return alertRepository.findById(id);
    }

    @Override
    public Alert createAlert(Alert alert) {

        return alertRepository.save(alert);
    }

    @Override
    public Alert updateAlert(Long id, Alert alert) {
        Optional<Alert> existingAlertOptional = alertRepository.findById(id);
        if (existingAlertOptional.isPresent()) {
            Alert existingAlert = existingAlertOptional.get();
            existingAlert.setType(alert.getType());
            existingAlert.setDate(alert.getDate());
            existingAlert.setCoordinates(alert.getCoordinates());
            existingAlert.setCommentaire(alert.getCommentaire());
            existingAlert.setIcon(alert.getIcon());
            return alertRepository.save(existingAlert);
        } else {
            throw new RuntimeException("Alert not found with id " + id);
        }
    }

    @Override
    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }

    private void updateAddressAttributes(Alert alert) {
        String coordinates = alert.getCoordinates();
        String[] latLon = coordinates.split(",");


    }
}
