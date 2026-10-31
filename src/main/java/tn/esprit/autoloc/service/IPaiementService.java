package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.PaiementDTO;

import java.util.List;
import java.util.Optional;

public interface IPaiementService {

    List<PaiementDTO> findAll();

    Optional<PaiementDTO> findById(Long id);

    PaiementDTO save(PaiementDTO paiementDTO);

    void deleteById(Long id);

    List<PaiementDTO> findByContratId(Long idContrat);
}
