package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.web.dto.PaiementDTO;

@Component
public class PaiementMapper {

    public PaiementDTO toDto(Paiement paiement) {
        if (paiement == null) {
            return null;
        }
        Long idContrat = paiement.getContrat() != null ? paiement.getContrat().getIdContrat() : null;
        return PaiementDTO.builder()
                .idPaiement(paiement.getIdPaiement())
                .montant(paiement.getMontant())
                .datePaiement(paiement.getDatePaiement())
                .modePaiement(paiement.getModePaiement())
                .idContrat(idContrat)
                .build();
    }

    public Paiement toEntity(PaiementDTO dto) {
        if (dto == null) {
            return null;
        }
        Paiement paiement = new Paiement();
        paiement.setIdPaiement(dto.getIdPaiement());
        paiement.setMontant(dto.getMontant());
        paiement.setDatePaiement(dto.getDatePaiement());
        paiement.setModePaiement(dto.getModePaiement());
        if (dto.getIdContrat() != null) {
            Contrat contrat = new Contrat();
            contrat.setIdContrat(dto.getIdContrat());
            paiement.setContrat(contrat);
        }
        return paiement;
    }
}
