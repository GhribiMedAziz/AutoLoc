package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.ReservationDTO;

@Component
public class ReservationMapper {

    public ReservationDTO toDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        Long idVehicule = reservation.getVehicule() != null ? reservation.getVehicule().getIdVehicule() : null;
        Long idClient = reservation.getClient() != null ? reservation.getClient().getIdClient() : null;
        return ReservationDTO.builder()
                .idReservation(reservation.getIdReservation())
                .dateDebut(reservation.getDateDebut())
                .dateFin(reservation.getDateFin())
                .statut(reservation.getStatut())
                .idVehicule(idVehicule)
                .idClient(idClient)
                .build();
    }

    public Reservation toEntity(ReservationDTO dto) {
        if (dto == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setIdReservation(dto.getIdReservation());
        reservation.setDateDebut(dto.getDateDebut());
        reservation.setDateFin(dto.getDateFin());
        reservation.setStatut(dto.getStatut());
        if (dto.getIdVehicule() != null) {
            Vehicule vehicule = new Vehicule();
            vehicule.setIdVehicule(dto.getIdVehicule());
            reservation.setVehicule(vehicule);
        }
        if (dto.getIdClient() != null) {
            Client client = new Client();
            client.setIdClient(dto.getIdClient());
            reservation.setClient(client);
        }
        return reservation;
    }
}
