package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Schema(description = "DTO representant une agence de location")
public class AgenceDTO {

    @Schema(description = "Identifiant unique de l'agence", example = "1")
    private Long idAgence;

    @NotBlank(message = "Le nom de l'agence est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit comporter entre 2 et 100 caracteres")
    @Schema(description = "Nom de l'agence", example = "AutoLoc Tunis Centre")
    private String nom;

    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 50, message = "La ville ne doit pas depasser 50 caracteres")
    @Schema(description = "Ville de situation de l'agence", example = "Tunis")
    private String ville;

    @NotBlank(message = "L'adresse est obligatoire")
    @Size(max = 255, message = "L'adresse ne doit pas depasser 255 caracteres")
    @Schema(description = "Adresse complete de l'agence", example = "20 Avenue de la Liberte")
    private String adresse;

    @NotBlank(message = "Le numero de telephone est obligatoire")
    @Pattern(regexp = "^[0-9]{8}$", message = "Le telephone doit comporter exactement 8 chiffres")
    @Schema(description = "Numero de telephone de contact (8 chiffres)", example = "71333444")
    private String telephone;
}
