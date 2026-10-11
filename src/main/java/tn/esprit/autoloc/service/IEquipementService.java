package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;
import java.util.Optional;

public interface IEquipementService {

    List<Equipement> findAll();

    Optional<Equipement> findById(Long id);

    Equipement save(Equipement equipement);

    void deleteById(Long id);
}
