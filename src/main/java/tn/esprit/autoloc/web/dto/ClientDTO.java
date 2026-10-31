package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO representant un client")
public class ClientDTO {

    @Schema(description = "Identifiant unique du client", example = "1")
    private Long idClient;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit comporter entre 2 et 50 caracteres")
    @Schema(description = "Nom de famille du client", example = "Ben Ali")
    private String nom;

    @NotBlank(message = "Le prenom est obligatoire")
    @Size(min = 2, max = 50, message = "Le prenom doit comporter entre 2 et 50 caracteres")
    @Schema(description = "Prenom du client", example = "Sami")
    private String prenom;

    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "Format d'adresse email invalide")
    @Schema(description = "Adresse email professionnelle ou personnelle", example = "sami.benali@esprit.tn")
    private String email;

    @NotBlank(message = "Le numero de telephone est obligatoire")
    @Pattern(regexp = "^[0-9]{8}$", message = "Le telephone doit comporter exactement 8 chiffres")
    @Schema(description = "Numero de telephone (8 chiffres)", example = "22111222")
    private String telephone;

    @NotBlank(message = "Le numero de permis est obligatoire")
    @Size(min = 5, max = 30, message = "Le numero de permis doit comporter entre 5 et 30 caracteres")
    @Schema(description = "Numero du permis de conduire", example = "TN-PERM-001")
    private String numPermis;

    @NotNull(message = "La date d'inscription est obligatoire")
    @PastOrPresent(message = "La date d'inscription ne peut pas etre dans le futur")
    @Schema(description = "Date d'enregistrement du client", example = "2026-10-18")
    private LocalDate dateInscription;
}
