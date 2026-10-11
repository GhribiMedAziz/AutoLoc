package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;

    @Autowired
    public AgenceServiceImpl(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Optional<Agence> findById(Long id) {
        return agenceRepository.findById(id);
    }

    @Override
    @Transactional
    public Agence save(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        agenceRepository.deleteById(id);
    }
}
