package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IContratService;
import tn.esprit.autoloc.web.dto.ContratDTO;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@Tag(name = "Contrats", description = "CRUD des contrats avec DTOs et validation")
public class ContratController {

    private final IContratService contratService;

    @Autowired
    public ContratController(IContratService contratService) {
        this.contratService = contratService;
    }

    @GetMapping
    @Operation(summary = "Lister les contrats", description = "Retourne tous les contrats sous forme de DTOs")
    public List<ContratDTO> getAllContrats() {
        return contratService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un contrat par ID")
    public ResponseEntity<ContratDTO> getContratById(@PathVariable Long id) {
        return contratService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable avec l'identifiant : " + id));
    }

    @GetMapping("/by-reservation/{idReservation}")
    @Operation(summary = "Consulter un contrat par identifiant de reservation")
    public ResponseEntity<ContratDTO> getContratByReservationId(@PathVariable Long idReservation) {
        return contratService.findByReservationId(idReservation)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable pour la reservation : " + idReservation));
    }

    @PostMapping
    @Operation(summary = "Creer un contrat avec validation")
    public ResponseEntity<ContratDTO> createContrat(@Valid @RequestBody ContratDTO contratDTO) {
        ContratDTO saved = contratService.save(contratDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un contrat")
    public ResponseEntity<ContratDTO> updateContrat(@PathVariable Long id, @Valid @RequestBody ContratDTO contratDTO) {
        if (contratService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Contrat introuvable pour mise a jour : " + id);
        }
        contratDTO.setIdContrat(id);
        return ResponseEntity.ok(contratService.save(contratDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        if (contratService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Contrat introuvable pour suppression : " + id);
        }
        contratService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
