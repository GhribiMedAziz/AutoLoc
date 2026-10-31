package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.domain.RoleEmploye;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO representant un employe")
public class EmployeDTO {

    private Long idEmploye;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit comporter entre 2 et 50 caracteres")
    private String nom;

    @NotBlank(message = "Le prenom est obligatoire")
    @Size(min = 2, max = 50, message = "Le prenom doit comporter entre 2 et 50 caracteres")
    private String prenom;

    @NotNull(message = "Le role est obligatoire")
    private RoleEmploye role;

    private Long idAgence;
}
