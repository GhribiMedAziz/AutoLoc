package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.RoleEmploye;
import tn.esprit.autoloc.mapper.EmployeMapper;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EmployeRepository;
import tn.esprit.autoloc.web.dto.EmployeDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;
    private final EmployeMapper employeMapper;

    @Autowired
    public EmployeServiceImpl(EmployeRepository employeRepository,
                              AgenceRepository agenceRepository,
                              EmployeMapper employeMapper) {
        this.employeRepository = employeRepository;
        this.agenceRepository = agenceRepository;
        this.employeMapper = employeMapper;
    }

    @Override
    public List<EmployeDTO> findAll() {
        return employeRepository.findAll()
                .stream()
                .map(employeMapper::toDto)
                .toList();
    }

    @Override
    public Optional<EmployeDTO> findById(Long id) {
        return employeRepository.findById(id)
                .map(employeMapper::toDto);
    }

    @Override
    @Transactional
    public EmployeDTO save(EmployeDTO employeDTO) {
        Employe employe = employeMapper.toEntity(employeDTO);
        if (employeDTO.getIdAgence() != null) {
            Optional<Agence> agenceOpt = agenceRepository.findById(employeDTO.getIdAgence());
            agenceOpt.ifPresent(employe::setAgence);
        }
        Employe saved = employeRepository.save(employe);
        return employeMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        employeRepository.deleteById(id);
    }

    @Override
    public List<EmployeDTO> findByAgenceId(Long idAgence) {
        return employeRepository.findByAgenceIdAgence(idAgence)
                .stream()
                .map(employeMapper::toDto)
                .toList();
    }

    @Override
    public List<EmployeDTO> findByRole(RoleEmploye role) {
        return employeRepository.findByRole(role)
                .stream()
                .map(employeMapper::toDto)
                .toList();
    }
}
