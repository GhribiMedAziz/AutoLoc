package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

import java.util.ArrayList;

@Component
public class VehiculeMapper {

    public VehiculeDTO toDto(Vehicule vehicule) {
        if (vehicule == null) {
            return null;
        }

        Long idAgence = null;
        String nomAgence = null;
        if (vehicule.getAgence() != null) {
            idAgence = vehicule.getAgence().getIdAgence();
            nomAgence = vehicule.getAgence().getNom();
        }

        return VehiculeDTO.builder()
                .idVehicule(vehicule.getIdVehicule())
                .immatriculation(vehicule.getImmatriculation())
                .marque(vehicule.getMarque())
                .modele(vehicule.getModele())
                .categorie(vehicule.getCategorie())
                .tarifJournalier(vehicule.getTarifJournalier())
                .statut(vehicule.getStatut())
                .idAgence(idAgence)
                .nomAgence(nomAgence)
                .build();
    }

    public Vehicule toEntity(VehiculeDTO dto) {
        if (dto == null) {
            return null;
        }

        Vehicule vehicule = new Vehicule();
        vehicule.setIdVehicule(dto.getIdVehicule());
        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setCategorie(dto.getCategorie());
        vehicule.setTarifJournalier(dto.getTarifJournalier());
        vehicule.setStatut(dto.getStatut());

        if (dto.getIdAgence() != null) {
            Agence agence = new Agence();
            agence.setIdAgence(dto.getIdAgence());
            vehicule.setAgence(agence);
        }

        vehicule.setMaintenances(new ArrayList<>());
        vehicule.setReservations(new ArrayList<>());
        vehicule.setEquipements(new ArrayList<>());

        return vehicule;
    }
}
