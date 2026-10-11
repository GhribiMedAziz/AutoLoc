package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ContratServiceImpl implements IContratService {

    private final ContratRepository contratRepository;

    @Autowired
    public ContratServiceImpl(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public Optional<Contrat> findById(Long id) {
        return contratRepository.findById(id);
    }

    @Override
    @Transactional
    public Contrat save(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public Optional<Contrat> findByReservationId(Long idReservation) {
        return contratRepository.findByReservationIdReservation(idReservation);
    }
}
