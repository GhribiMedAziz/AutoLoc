package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.mapper.ReservationMapper;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.ReservationDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final VehiculeRepository vehiculeRepository;
    private final ClientRepository clientRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  VehiculeRepository vehiculeRepository,
                                  ClientRepository clientRepository,
                                  ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.clientRepository = clientRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<ReservationDTO> findAll() {
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ReservationDTO> findById(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toDto);
    }

    @Override
    @Transactional
    public ReservationDTO save(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        if (reservationDTO.getIdVehicule() != null) {
            Optional<Vehicule> vehiculeOpt = vehiculeRepository.findById(reservationDTO.getIdVehicule());
            vehiculeOpt.ifPresent(reservation::setVehicule);
        }
        if (reservationDTO.getIdClient() != null) {
            Optional<Client> clientOpt = clientRepository.findById(reservationDTO.getIdClient());
            clientOpt.ifPresent(reservation::setClient);
        }
        Reservation saved = reservationRepository.save(reservation);
        return reservationMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationDTO> findByClientId(Long idClient) {
        return reservationRepository.findByClientIdClient(idClient)
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    @Override
    public List<ReservationDTO> findByVehiculeId(Long idVehicule) {
        return reservationRepository.findByVehiculeIdVehicule(idVehicule)
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    @Override
    public List<ReservationDTO> findByStatut(StatutReservation statut) {
        return reservationRepository.findByStatut(statut)
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }
}
