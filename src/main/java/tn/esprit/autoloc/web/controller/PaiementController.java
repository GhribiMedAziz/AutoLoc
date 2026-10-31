package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IPaiementService;
import tn.esprit.autoloc.web.dto.PaiementDTO;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@Tag(name = "Paiements", description = "CRUD des paiements avec DTOs et validation")
public class PaiementController {

    private final IPaiementService paiementService;

    @Autowired
    public PaiementController(IPaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @GetMapping
    @Operation(summary = "Lister les paiements")
    public List<PaiementDTO> getAllPaiements() {
        return paiementService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un paiement par ID")
    public ResponseEntity<PaiementDTO> getPaiementById(@PathVariable Long id) {
        return paiementService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement introuvable avec l'identifiant : " + id));
    }

    @GetMapping("/by-contrat/{idContrat}")
    @Operation(summary = "Lister les paiements d'un contrat")
    public List<PaiementDTO> getPaiementsByContratId(@PathVariable Long idContrat) {
        return paiementService.findByContratId(idContrat);
    }

    @PostMapping
    @Operation(summary = "Enregistrer un paiement avec validation")
    public ResponseEntity<PaiementDTO> createPaiement(@Valid @RequestBody PaiementDTO paiementDTO) {
        PaiementDTO saved = paiementService.save(paiementDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un paiement")
    public ResponseEntity<PaiementDTO> updatePaiement(@PathVariable Long id, @Valid @RequestBody PaiementDTO paiementDTO) {
        if (paiementService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Paiement introuvable pour mise a jour : " + id);
        }
        paiementDTO.setIdPaiement(id);
        return ResponseEntity.ok(paiementService.save(paiementDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        if (paiementService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Paiement introuvable pour suppression : " + id);
        }
        paiementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
