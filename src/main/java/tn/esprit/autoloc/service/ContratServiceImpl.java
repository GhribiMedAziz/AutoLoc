package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.mapper.ContratMapper;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.web.dto.ContratDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ContratServiceImpl implements IContratService {

    private final ContratRepository contratRepository;
    private final ContratMapper contratMapper;

    @Autowired
    public ContratServiceImpl(ContratRepository contratRepository, ContratMapper contratMapper) {
        this.contratRepository = contratRepository;
        this.contratMapper = contratMapper;
    }

    @Override
    public List<ContratDTO> findAll() {
        return contratRepository.findAll()
                .stream()
                .map(contratMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ContratDTO> findById(Long id) {
        return contratRepository.findById(id)
                .map(contratMapper::toDto);
    }

    @Override
    @Transactional
    public ContratDTO save(ContratDTO contratDTO) {
        Contrat contrat = contratMapper.toEntity(contratDTO);
        Contrat saved = contratRepository.save(contrat);
        return contratMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public Optional<ContratDTO> findByReservationId(Long idReservation) {
        return contratRepository.findByReservationIdReservation(idReservation)
                .map(contratMapper::toDto);
    }
}
