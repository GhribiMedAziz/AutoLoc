package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.EquipementDTO;

import java.util.List;
import java.util.Optional;

public interface IEquipementService {

    List<EquipementDTO> findAll();

    Optional<EquipementDTO> findById(Long id);

    EquipementDTO save(EquipementDTO equipementDTO);

    void deleteById(Long id);
}
