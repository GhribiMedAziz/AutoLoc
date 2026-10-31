package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.RoleEmploye;
import tn.esprit.autoloc.web.dto.EmployeDTO;

import java.util.List;
import java.util.Optional;

public interface IEmployeService {

    List<EmployeDTO> findAll();

    Optional<EmployeDTO> findById(Long id);

    EmployeDTO save(EmployeDTO employeDTO);

    void deleteById(Long id);

    List<EmployeDTO> findByAgenceId(Long idAgence);

    List<EmployeDTO> findByRole(RoleEmploye role);
}
