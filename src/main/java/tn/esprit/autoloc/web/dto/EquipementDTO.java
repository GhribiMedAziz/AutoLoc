package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO representant un equipement de vehicule")
public class EquipementDTO {

    private Long idEquipement;

    @NotBlank(message = "Le libelle de l'equipement est obligatoire")
    @Size(min = 2, max = 50, message = "Le libelle doit comporter entre 2 et 50 caracteres")
    private String libelle;
}
