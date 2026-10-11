package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    Reservation save(Reservation reservation);

    void deleteById(Long id);

    List<Reservation> findByClientId(Long idClient);

    List<Reservation> findByStatut(StatutReservation statut);
}
