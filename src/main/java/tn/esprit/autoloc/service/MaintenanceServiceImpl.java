package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.mapper.MaintenanceMapper;
import tn.esprit.autoloc.repository.MaintenanceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.MaintenanceDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehiculeRepository vehiculeRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository,
                                  VehiculeRepository vehiculeRepository,
                                  MaintenanceMapper maintenanceMapper) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.maintenanceMapper = maintenanceMapper;
    }

    @Override
    public List<MaintenanceDTO> findAll() {
        return maintenanceRepository.findAll()
                .stream()
                .map(maintenanceMapper::toDto)
                .toList();
    }

    @Override
    public Optional<MaintenanceDTO> findById(Long id) {
        return maintenanceRepository.findById(id)
                .map(maintenanceMapper::toDto);
    }

    @Override
    @Transactional
    public MaintenanceDTO save(MaintenanceDTO maintenanceDTO) {
        Maintenance maintenance = maintenanceMapper.toEntity(maintenanceDTO);
        if (maintenanceDTO.getIdVehicule() != null) {
            Optional<Vehicule> vehiculeOpt = vehiculeRepository.findById(maintenanceDTO.getIdVehicule());
            vehiculeOpt.ifPresent(maintenance::setVehicule);
        }
        Maintenance saved = maintenanceRepository.save(maintenance);
        return maintenanceMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        maintenanceRepository.deleteById(id);
    }

    @Override
    public List<MaintenanceDTO> findByVehiculeId(Long idVehicule) {
        return maintenanceRepository.findByVehiculeIdVehicule(idVehicule)
                .stream()
                .map(maintenanceMapper::toDto)
                .toList();
    }
}
