package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByClientIdClient(Long idClient);

    List<Reservation> findByVehiculeIdVehicule(Long idVehicule);

    List<Reservation> findByStatut(StatutReservation statut);
}
