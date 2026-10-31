package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.VehiculeDTO;

import java.util.List;
import java.util.Optional;

public interface IVehiculeService {

    List<VehiculeDTO> findAll();

    Optional<VehiculeDTO> findById(Long id);

    VehiculeDTO save(VehiculeDTO vehiculeDTO);

    void deleteById(Long id);

    boolean existsByImmatriculation(String immatriculation);
}
