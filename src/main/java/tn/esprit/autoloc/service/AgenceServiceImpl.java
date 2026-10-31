package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.mapper.AgenceMapper;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.web.dto.AgenceDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    @Autowired
    public AgenceServiceImpl(AgenceRepository agenceRepository, AgenceMapper agenceMapper) {
        this.agenceRepository = agenceRepository;
        this.agenceMapper = agenceMapper;
    }

    @Override
    public List<AgenceDTO> findAll() {
        return agenceRepository.findAll()
                .stream()
                .map(agenceMapper::toDto)
                .toList();
    }

    @Override
    public Optional<AgenceDTO> findById(Long id) {
        return agenceRepository.findById(id)
                .map(agenceMapper::toDto);
    }

    @Override
    @Transactional
    public AgenceDTO save(AgenceDTO agenceDTO) {
        Agence agence = agenceMapper.toEntity(agenceDTO);
        Agence savedAgence = agenceRepository.save(agence);
        return agenceMapper.toDto(savedAgence);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        agenceRepository.deleteById(id);
    }
}
