package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.web.dto.AgenceDTO;

import java.util.ArrayList;

@Component
public class AgenceMapper {

    public AgenceDTO toDto(Agence agence) {
        if (agence == null) {
            return null;
        }
        return AgenceDTO.builder()
                .idAgence(agence.getIdAgence())
                .nom(agence.getNom())
                .ville(agence.getVille())
                .adresse(agence.getAdresse())
                .telephone(agence.getTelephone())
                .build();
    }

    public Agence toEntity(AgenceDTO dto) {
        if (dto == null) {
            return null;
        }
        Agence agence = new Agence();
        agence.setIdAgence(dto.getIdAgence());
        agence.setNom(dto.getNom());
        agence.setVille(dto.getVille());
        agence.setAdresse(dto.getAdresse());
        agence.setTelephone(dto.getTelephone());
        agence.setEmployes(new ArrayList<>());
        agence.setVehicules(new ArrayList<>());
        return agence;
    }
}
