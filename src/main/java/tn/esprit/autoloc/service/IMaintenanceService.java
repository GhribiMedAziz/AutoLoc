package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;
import java.util.Optional;

public interface IMaintenanceService {

    List<Maintenance> findAll();

    Optional<Maintenance> findById(Long id);

    Maintenance save(Maintenance maintenance);

    void deleteById(Long id);

    List<Maintenance> findByVehiculeId(Long idVehicule);
}
