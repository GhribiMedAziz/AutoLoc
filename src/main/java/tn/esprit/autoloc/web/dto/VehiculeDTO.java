package tn.esprit.autoloc.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO representant un vehicule de la flotte")
public class VehiculeDTO {

    @Schema(description = "Identifiant unique du vehicule", example = "1")
    private Long idVehicule;

    @NotBlank(message = "L'immatriculation est obligatoire")
    @Pattern(regexp = "^[0-9]{1,3}-TN-[0-9]{1,4}$", message = "Format immatriculation invalide (ex: 200-TN-1234)")
    @Schema(description = "Immatriculation tunisienne du vehicule", example = "200-TN-1234")
    private String immatriculation;

    @NotBlank(message = "La marque est obligatoire")
    @Size(min = 2, max = 50, message = "La marque doit comporter entre 2 et 50 caracteres")
    @Schema(description = "Marque du constructeur", example = "Renault")
    private String marque;

    @NotBlank(message = "Le modele est obligatoire")
    @Size(min = 1, max = 50, message = "Le modele doit comporter entre 1 et 50 caracteres")
    @Schema(description = "Modele commercial", example = "Clio 5")
    private String modele;

    @NotNull(message = "La categorie est obligatoire")
    @Schema(description = "Categorie de gabarit", example = "CITADINE")
    private CategorieVehicule categorie;

    @NotNull(message = "Le tarif journalier est obligatoire")
    @Positive(message = "Le tarif journalier doit etre strictement superieur a zero")
    @Schema(description = "Prix de location par jour en Dinars tunisiens", example = "90.00")
    private BigDecimal tarifJournalier;

    @NotNull(message = "Le statut est obligatoire")
    @Schema(description = "Statut actuel de disponibilite", example = "DISPONIBLE")
    private StatutVehicule statut;

    @Schema(description = "Identifiant de l'agence d'affectation", example = "1")
    private Long idAgence;

    @Schema(description = "Nom de l'agence de rattachement", example = "AutoLoc Tunis Centre")
    private String nomAgence;
}
