package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.AgenceDTO;

import java.util.List;
import java.util.Optional;

public interface IAgenceService {

    List<AgenceDTO> findAll();

    Optional<AgenceDTO> findById(Long id);

    AgenceDTO save(AgenceDTO agenceDTO);

    void deleteById(Long id);
}
