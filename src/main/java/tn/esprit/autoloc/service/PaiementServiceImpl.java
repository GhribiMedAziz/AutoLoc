package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.PaiementRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaiementServiceImpl implements IPaiementService {

    private final PaiementRepository paiementRepository;

    @Autowired
    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    @Override
    public Optional<Paiement> findById(Long id) {
        return paiementRepository.findById(id);
    }

    @Override
    @Transactional
    public Paiement save(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        paiementRepository.deleteById(id);
    }

    @Override
    public List<Paiement> findByContratId(Long idContrat) {
        return paiementRepository.findByContratIdContrat(idContrat);
    }
}
