package app.mobility.Smart_mobility.Web.Controller;

import app.mobility.Smart_mobility.Model.Alert;
import app.mobility.Smart_mobility.Service.Interf.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alert")
public class AlertController {
    @Autowired
    private AlertService alertService;

    // Endpoint pour récupérer toutes les alertes
    @GetMapping("/getAll")
    public ResponseEntity<List<Alert>> getAllAlerts() {
        List<Alert> alerts = alertService.getAllAlerts();
        return new ResponseEntity<>(alerts, HttpStatus.OK);
    }
    // Endpoint pour récupérer une alerte par son id
    @GetMapping("/get/{id}")
    public ResponseEntity<Alert> getAlertById(@PathVariable Long id) {
        Optional<Alert> alert = alertService.getAlertById(id);
        return alert.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pour créer une nouvelle alerte
    @PostMapping("/create")
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        Alert createdAlert = alertService.createAlert(alert);
        return new ResponseEntity<>(createdAlert, HttpStatus.CREATED);
    }
    // Endpoint pour mettre à jour une alerte existante
    @PutMapping("/update/{id}")
    public ResponseEntity<Alert> updateAlert(@PathVariable Long id, @RequestBody Alert alert) {
        Alert updatedAlert = alertService.updateAlert(id, alert);
        return new ResponseEntity<>(updatedAlert, HttpStatus.OK);
    }

    // Endpoint pour supprimer une alerte par son id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }
}
