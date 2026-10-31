package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.mapper.PaiementMapper;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.PaiementRepository;
import tn.esprit.autoloc.web.dto.PaiementDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaiementServiceImpl implements IPaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratRepository contratRepository;
    private final PaiementMapper paiementMapper;

    @Autowired
    public PaiementServiceImpl(PaiementRepository paiementRepository,
                               ContratRepository contratRepository,
                               PaiementMapper paiementMapper) {
        this.paiementRepository = paiementRepository;
        this.contratRepository = contratRepository;
        this.paiementMapper = paiementMapper;
    }

    @Override
    public List<PaiementDTO> findAll() {
        return paiementRepository.findAll()
                .stream()
                .map(paiementMapper::toDto)
                .toList();
    }

    @Override
    public Optional<PaiementDTO> findById(Long id) {
        return paiementRepository.findById(id)
                .map(paiementMapper::toDto);
    }

    @Override
    @Transactional
    public PaiementDTO save(PaiementDTO paiementDTO) {
        Paiement paiement = paiementMapper.toEntity(paiementDTO);
        if (paiementDTO.getIdContrat() != null) {
            Optional<Contrat> contratOpt = contratRepository.findById(paiementDTO.getIdContrat());
            contratOpt.ifPresent(paiement::setContrat);
        }
        Paiement saved = paiementRepository.save(paiement);
        return paiementMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        paiementRepository.deleteById(id);
    }

    @Override
    public List<PaiementDTO> findByContratId(Long idContrat) {
        return paiementRepository.findByContratIdContrat(idContrat)
                .stream()
                .map(paiementMapper::toDto)
                .toList();
    }
}
