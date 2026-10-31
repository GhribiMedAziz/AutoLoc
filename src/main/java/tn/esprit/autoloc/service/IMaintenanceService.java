package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.MaintenanceDTO;

import java.util.List;
import java.util.Optional;

public interface IMaintenanceService {

    List<MaintenanceDTO> findAll();

    Optional<MaintenanceDTO> findById(Long id);

    MaintenanceDTO save(MaintenanceDTO maintenanceDTO);

    void deleteById(Long id);

    List<MaintenanceDTO> findByVehiculeId(Long idVehicule);
}
