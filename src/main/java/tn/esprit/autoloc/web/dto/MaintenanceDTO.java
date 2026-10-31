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

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO representant une maintenance")
public class MaintenanceDTO {

    private Long idMaintenance;

    @NotNull(message = "La date de debut est obligatoire")
    private LocalDate dateDebut;

    private LocalDate dateFin;

    @NotBlank(message = "La description est obligatoire")
    @Size(max = 255, message = "La description ne doit pas depasser 255 caracteres")
    private String description;

    private Long idVehicule;
}
