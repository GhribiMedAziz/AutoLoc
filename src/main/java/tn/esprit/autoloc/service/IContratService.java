package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.ContratDTO;

import java.util.List;
import java.util.Optional;

public interface IContratService {

    List<ContratDTO> findAll();

    Optional<ContratDTO> findById(Long id);

    ContratDTO save(ContratDTO contratDTO);

    void deleteById(Long id);

    Optional<ContratDTO> findByReservationId(Long idReservation);
}
