package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;
import java.util.Optional;

public interface IAgenceService {

    List<Agence> findAll();

    Optional<Agence> findById(Long id);

    Agence save(Agence agence);

    void deleteById(Long id);
}
