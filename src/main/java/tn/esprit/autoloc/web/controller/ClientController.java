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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.web.dto.ClientDTO;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "CRUD des clients avec DTOs et validation")
public class ClientController {

    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Operation(summary = "Lister les clients", description = "Retourne tous les clients sous forme de DTOs")
    @ApiResponse(responseCode = "200", description = "Liste des clients recuperee avec succes")
    public List<ClientDTO> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un client", description = "Retourne un client sous forme de DTO a partir de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouve"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return clientService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable avec l'identifiant : " + id));
    }

    @GetMapping("/by-email")
    @Operation(summary = "Rechercher un client par email", description = "Retourne un client a partir de son adresse email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouve"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    public ResponseEntity<ClientDTO> getClientByEmail(@RequestParam String email) {
        return clientService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun client trouve avec l'adresse email : " + email));
    }

    @PostMapping
    @Operation(summary = "Creer un client", description = "Ajoute un nouveau client avec validation des donnees")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client cree avec succes"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides ou erreurs de validation")
    })
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO savedClient = clientService.save(clientDTO);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client", description = "Met a jour un client existant avec validation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client mis a jour avec succes"),
            @ApiResponse(responseCode = "400", description = "Donnees de mise a jour invalides"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        if (clientService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Client introuvable pour mise a jour avec l'identifiant : " + id);
        }
        clientDTO.setIdClient(id);
        return ResponseEntity.ok(clientService.save(clientDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client", description = "Supprime un client a partir de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprime avec succes"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Client introuvable pour suppression avec l'identifiant : " + id);
        }
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
