package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;
import java.util.Optional;

public interface IVehiculeService {

    List<Vehicule> findAll();

    Optional<Vehicule> findById(Long id);

    Vehicule save(Vehicule vehicule);

    void deleteById(Long id);

    boolean existsByImmatriculation(String immatriculation);
}
