package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.mapper.VehiculeMapper;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VehiculeService implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final VehiculeMapper vehiculeMapper;

    @Autowired
    public VehiculeService(VehiculeRepository vehiculeRepository,
                           AgenceRepository agenceRepository,
                           VehiculeMapper vehiculeMapper) {
        this.vehiculeRepository = vehiculeRepository;
        this.agenceRepository = agenceRepository;
        this.vehiculeMapper = vehiculeMapper;
    }

    @Override
    public List<VehiculeDTO> findAll() {
        return vehiculeRepository.findAll()
                .stream()
                .map(vehiculeMapper::toDto)
                .toList();
    }

    @Override
    public Optional<VehiculeDTO> findById(Long id) {
        return vehiculeRepository.findById(id)
                .map(vehiculeMapper::toDto);
    }

    @Override
    @Transactional
    public VehiculeDTO save(VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = vehiculeMapper.toEntity(vehiculeDTO);
        if (vehiculeDTO.getIdAgence() != null) {
            Optional<Agence> agenceOpt = agenceRepository.findById(vehiculeDTO.getIdAgence());
            agenceOpt.ifPresent(vehicule::setAgence);
        }
        Vehicule savedVehicule = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(savedVehicule);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public boolean existsByImmatriculation(String immatriculation) {
        return vehiculeRepository.existsByImmatriculation(immatriculation);
    }
}
