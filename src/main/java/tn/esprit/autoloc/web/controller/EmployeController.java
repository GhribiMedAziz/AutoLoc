package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.RoleEmploye;
import tn.esprit.autoloc.service.IEmployeService;
import tn.esprit.autoloc.web.dto.EmployeDTO;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@Tag(name = "Employes", description = "CRUD des employes avec DTOs et validation")
public class EmployeController {

    private final IEmployeService employeService;

    @Autowired
    public EmployeController(IEmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    @Operation(summary = "Lister les employes")
    public List<EmployeDTO> getAllEmployes() {
        return employeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un employe par ID")
    public ResponseEntity<EmployeDTO> getEmployeById(@PathVariable Long id) {
        return employeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Employe introuvable avec l'identifiant : " + id));
    }

    @GetMapping("/by-agence/{idAgence}")
    @Operation(summary = "Lister les employes d'une agence")
    public List<EmployeDTO> getEmployesByAgenceId(@PathVariable Long idAgence) {
        return employeService.findByAgenceId(idAgence);
    }

    @GetMapping("/by-role/{role}")
    @Operation(summary = "Lister les employes par role")
    public List<EmployeDTO> getEmployesByRole(@PathVariable RoleEmploye role) {
        return employeService.findByRole(role);
    }

    @PostMapping
    @Operation(summary = "Creer un employe avec validation")
    public ResponseEntity<EmployeDTO> createEmploye(@Valid @RequestBody EmployeDTO employeDTO) {
        EmployeDTO saved = employeService.save(employeDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un employe")
    public ResponseEntity<EmployeDTO> updateEmploye(@PathVariable Long id, @Valid @RequestBody EmployeDTO employeDTO) {
        if (employeService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Employe introuvable pour mise a jour : " + id);
        }
        employeDTO.setIdEmploye(id);
        return ResponseEntity.ok(employeService.save(employeDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un employe")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        if (employeService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Employe introuvable pour suppression : " + id);
        }
        employeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
