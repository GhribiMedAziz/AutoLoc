package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.web.dto.EquipementDTO;

import java.util.ArrayList;

@Component
public class EquipementMapper {

    public EquipementDTO toDto(Equipement equipement) {
        if (equipement == null) {
            return null;
        }
        return EquipementDTO.builder()
                .idEquipement(equipement.getIdEquipement())
                .libelle(equipement.getLibelle())
                .build();
    }

    public Equipement toEntity(EquipementDTO dto) {
        if (dto == null) {
            return null;
        }
        Equipement equipement = new Equipement();
        equipement.setIdEquipement(dto.getIdEquipement());
        equipement.setLibelle(dto.getLibelle());
        equipement.setVehicules(new ArrayList<>());
        return equipement;
    }
}
