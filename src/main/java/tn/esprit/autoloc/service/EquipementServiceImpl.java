package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.EquipementRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EquipementServiceImpl implements IEquipementService {

    private final EquipementRepository equipementRepository;

    @Autowired
    public EquipementServiceImpl(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    @Override
    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    @Override
    public Optional<Equipement> findById(Long id) {
        return equipementRepository.findById(id);
    }

    @Override
    @Transactional
    public Equipement save(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        equipementRepository.deleteById(id);
    }
}
