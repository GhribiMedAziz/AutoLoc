package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;
import java.util.Optional;

public interface IPaiementService {

    List<Paiement> findAll();

    Optional<Paiement> findById(Long id);

    Paiement save(Paiement paiement);

    void deleteById(Long id);

    List<Paiement> findByContratId(Long idContrat);
}
