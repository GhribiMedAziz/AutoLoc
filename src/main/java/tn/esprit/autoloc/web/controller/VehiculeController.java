package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.VehiculeDTO;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@Tag(name = "Vehicules", description = "CRUD des vehicules avec DTOs et validation")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @Autowired
    public VehiculeController(IVehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping
    @Operation(summary = "Lister les vehicules", description = "Retourne tous les vehicules sous forme de DTOs")
    @ApiResponse(responseCode = "200", description = "Liste des vehicules recuperee avec succes")
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un vehicule", description = "Retourne un vehicule sous forme de DTO a partir de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule trouve"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public ResponseEntity<VehiculeDTO> getVehiculeById(@PathVariable Long id) {
        return vehiculeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule introuvable avec l'identifiant : " + id));
    }

    @PostMapping
    @Operation(summary = "Creer un vehicule", description = "Ajoute un nouveau vehicule avec controle de saisie Bean Validation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicule cree avec succes"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides ou erreurs de validation")
    })
    public ResponseEntity<VehiculeDTO> createVehicule(@Valid @RequestBody VehiculeDTO vehiculeDTO) {
        VehiculeDTO savedVehicule = vehiculeService.save(vehiculeDTO);
        return new ResponseEntity<>(savedVehicule, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un vehicule", description = "Met a jour un vehicule existant avec validation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule mis a jour avec succes"),
            @ApiResponse(responseCode = "400", description = "Donnees de mise a jour invalides"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public ResponseEntity<VehiculeDTO> updateVehicule(@PathVariable Long id, @Valid @RequestBody VehiculeDTO vehiculeDTO) {
        if (vehiculeService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Vehicule introuvable pour mise a jour avec l'identifiant : " + id);
        }
        vehiculeDTO.setIdVehicule(id);
        return ResponseEntity.ok(vehiculeService.save(vehiculeDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un vehicule", description = "Supprime un vehicule a partir de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vehicule supprime avec succes"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        if (vehiculeService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Vehicule introuvable pour suppression avec l'identifiant : " + id);
        }
        vehiculeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
