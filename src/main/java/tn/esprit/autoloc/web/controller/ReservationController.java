package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.service.IReservationService;
import tn.esprit.autoloc.web.dto.ReservationDTO;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservations", description = "CRUD des reservations avec DTOs et validation")
public class ReservationController {

    private final IReservationService reservationService;

    @Autowired
    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @Operation(summary = "Lister les reservations")
    public List<ReservationDTO> getAllReservations() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une reservation par ID")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        return reservationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation introuvable avec l'identifiant : " + id));
    }

    @GetMapping("/by-client/{idClient}")
    @Operation(summary = "Lister les reservations d'un client")
    public List<ReservationDTO> getReservationsByClientId(@PathVariable Long idClient) {
        return reservationService.findByClientId(idClient);
    }

    @GetMapping("/by-statut/{statut}")
    @Operation(summary = "Lister les reservations par statut")
    public List<ReservationDTO> getReservationsByStatut(@PathVariable StatutReservation statut) {
        return reservationService.findByStatut(statut);
    }

    @PostMapping
    @Operation(summary = "Creer une reservation avec validation")
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO saved = reservationService.save(reservationDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une reservation")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationDTO reservationDTO) {
        if (reservationService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Reservation introuvable pour mise a jour : " + id);
        }
        reservationDTO.setIdReservation(id);
        return ResponseEntity.ok(reservationService.save(reservationDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une reservation")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (reservationService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Reservation introuvable pour suppression : " + id);
        }
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
