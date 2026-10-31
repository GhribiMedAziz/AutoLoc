package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.web.dto.ContratDTO;

import java.util.ArrayList;

@Component
public class ContratMapper {

    public ContratDTO toDto(Contrat contrat) {
        if (contrat == null) {
            return null;
        }
        Long idReservation = contrat.getReservation() != null ? contrat.getReservation().getIdReservation() : null;
        return ContratDTO.builder()
                .idContrat(contrat.getIdContrat())
                .dateSignature(contrat.getDateSignature())
                .montantTotal(contrat.getMontantTotal())
                .valide(contrat.isValide())
                .idReservation(idReservation)
                .build();
    }

    public Contrat toEntity(ContratDTO dto) {
        if (dto == null) {
            return null;
        }
        Contrat contrat = new Contrat();
        contrat.setIdContrat(dto.getIdContrat());
        contrat.setDateSignature(dto.getDateSignature());
        contrat.setMontantTotal(dto.getMontantTotal());
        contrat.setValide(dto.isValide());
        if (dto.getIdReservation() != null) {
            Reservation reservation = new Reservation();
            reservation.setIdReservation(dto.getIdReservation());
            contrat.setReservation(reservation);
        }
        contrat.setPaiements(new ArrayList<>());
        return contrat;
    }
}
