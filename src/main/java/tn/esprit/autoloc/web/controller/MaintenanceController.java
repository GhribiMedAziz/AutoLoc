package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IMaintenanceService;
import tn.esprit.autoloc.web.dto.MaintenanceDTO;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@Tag(name = "Maintenances", description = "CRUD des maintenances avec DTOs et validation")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @Autowired
    public MaintenanceController(IMaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    @Operation(summary = "Lister les maintenances")
    public List<MaintenanceDTO> getAllMaintenances() {
        return maintenanceService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une maintenance par ID")
    public ResponseEntity<MaintenanceDTO> getMaintenanceById(@PathVariable Long id) {
        return maintenanceService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance introuvable avec l'identifiant : " + id));
    }

    @GetMapping("/by-vehicule/{idVehicule}")
    @Operation(summary = "Lister les maintenances d'un vehicule")
    public List<MaintenanceDTO> getMaintenancesByVehiculeId(@PathVariable Long idVehicule) {
        return maintenanceService.findByVehiculeId(idVehicule);
    }

    @PostMapping
    @Operation(summary = "Creer une maintenance avec validation")
    public ResponseEntity<MaintenanceDTO> createMaintenance(@Valid @RequestBody MaintenanceDTO maintenanceDTO) {
        MaintenanceDTO saved = maintenanceService.save(maintenanceDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une maintenance")
    public ResponseEntity<MaintenanceDTO> updateMaintenance(@PathVariable Long id, @Valid @RequestBody MaintenanceDTO maintenanceDTO) {
        if (maintenanceService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Maintenance introuvable pour mise a jour : " + id);
        }
        maintenanceDTO.setIdMaintenance(id);
        return ResponseEntity.ok(maintenanceService.save(maintenanceDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une maintenance")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        if (maintenanceService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Maintenance introuvable pour suppression : " + id);
        }
        maintenanceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
