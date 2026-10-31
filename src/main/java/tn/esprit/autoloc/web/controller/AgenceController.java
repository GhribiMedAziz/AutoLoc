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
import tn.esprit.autoloc.service.IAgenceService;
import tn.esprit.autoloc.web.dto.AgenceDTO;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@Tag(name = "Agences", description = "CRUD des agences avec DTOs et validation")
public class AgenceController {

    private final IAgenceService agenceService;

    @Autowired
    public AgenceController(IAgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @GetMapping
    @Operation(summary = "Lister les agences", description = "Retourne toutes les agences sous forme de DTOs")
    @ApiResponse(responseCode = "200", description = "Liste des agences recuperee avec succes")
    public List<AgenceDTO> getAllAgences() {
        return agenceService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une agence", description = "Retourne une agence sous forme de DTO a partir de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvee"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    public ResponseEntity<AgenceDTO> getAgenceById(@PathVariable Long id) {
        return agenceService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'identifiant : " + id));
    }

    @PostMapping
    @Operation(summary = "Creer une agence", description = "Ajoute une nouvelle agence avec validation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agence creee avec succes"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides ou erreurs de validation")
    })
    public ResponseEntity<AgenceDTO> createAgence(@Valid @RequestBody AgenceDTO agenceDTO) {
        AgenceDTO savedAgence = agenceService.save(agenceDTO);
        return new ResponseEntity<>(savedAgence, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une agence", description = "Met a jour une agence existante avec validation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence mise a jour avec succes"),
            @ApiResponse(responseCode = "400", description = "Donnees de mise a jour invalides"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    public ResponseEntity<AgenceDTO> updateAgence(@PathVariable Long id, @Valid @RequestBody AgenceDTO agenceDTO) {
        if (agenceService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Agence introuvable pour mise a jour avec l'identifiant : " + id);
        }
        agenceDTO.setIdAgence(id);
        return ResponseEntity.ok(agenceService.save(agenceDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une agence", description = "Supprime une agence a partir de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Agence supprimee avec succes"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    public ResponseEntity<Void> deleteAgence(@PathVariable Long id) {
        if (agenceService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Agence introuvable pour suppression avec l'identifiant : " + id);
        }
        agenceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
