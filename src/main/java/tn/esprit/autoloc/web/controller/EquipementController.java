package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IEquipementService;
import tn.esprit.autoloc.web.dto.EquipementDTO;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@Tag(name = "Equipements", description = "CRUD des equipements avec DTOs et validation")
public class EquipementController {

    private final IEquipementService equipementService;

    @Autowired
    public EquipementController(IEquipementService equipementService) {
        this.equipementService = equipementService;
    }

    @GetMapping
    @Operation(summary = "Lister les equipements")
    public List<EquipementDTO> getAllEquipements() {
        return equipementService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un equipement par ID")
    public ResponseEntity<EquipementDTO> getEquipementById(@PathVariable Long id) {
        return equipementService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement introuvable avec l'identifiant : " + id));
    }

    @PostMapping
    @Operation(summary = "Creer un equipement avec validation")
    public ResponseEntity<EquipementDTO> createEquipement(@Valid @RequestBody EquipementDTO equipementDTO) {
        EquipementDTO saved = equipementService.save(equipementDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un equipement")
    public ResponseEntity<EquipementDTO> updateEquipement(@PathVariable Long id, @Valid @RequestBody EquipementDTO equipementDTO) {
        if (equipementService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Equipement introuvable pour mise a jour : " + id);
        }
        equipementDTO.setIdEquipement(id);
        return ResponseEntity.ok(equipementService.save(equipementDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un equipement")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long id) {
        if (equipementService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Equipement introuvable pour suppression : " + id);
        }
        equipementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
