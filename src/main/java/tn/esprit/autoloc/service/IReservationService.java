package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.web.dto.ReservationDTO;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    List<ReservationDTO> findAll();

    Optional<ReservationDTO> findById(Long id);

    ReservationDTO save(ReservationDTO reservationDTO);

    void deleteById(Long id);

    List<ReservationDTO> findByClientId(Long idClient);

    List<ReservationDTO> findByVehiculeId(Long idVehicule);

    List<ReservationDTO> findByStatut(StatutReservation statut);
}
