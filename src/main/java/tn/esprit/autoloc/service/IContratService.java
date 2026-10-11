package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;
import java.util.Optional;

public interface IContratService {

    List<Contrat> findAll();

    Optional<Contrat> findById(Long id);

    Contrat save(Contrat contrat);

    void deleteById(Long id);

    Optional<Contrat> findByReservationId(Long idReservation);
}
