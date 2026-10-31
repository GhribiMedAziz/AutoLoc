package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.mapper.EquipementMapper;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.web.dto.EquipementDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EquipementServiceImpl implements IEquipementService {

    private final EquipementRepository equipementRepository;
    private final EquipementMapper equipementMapper;

    @Autowired
    public EquipementServiceImpl(EquipementRepository equipementRepository, EquipementMapper equipementMapper) {
        this.equipementRepository = equipementRepository;
        this.equipementMapper = equipementMapper;
    }

    @Override
    public List<EquipementDTO> findAll() {
        return equipementRepository.findAll()
                .stream()
                .map(equipementMapper::toDto)
                .toList();
    }

    @Override
    public Optional<EquipementDTO> findById(Long id) {
        return equipementRepository.findById(id)
                .map(equipementMapper::toDto);
    }

    @Override
    @Transactional
    public EquipementDTO save(EquipementDTO equipementDTO) {
        Equipement equipement = equipementMapper.toEntity(equipementDTO);
        Equipement saved = equipementRepository.save(equipement);
        return equipementMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        equipementRepository.deleteById(id);
    }
}
