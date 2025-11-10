package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingRequestDTO {

    // CAMPOS COMUNS (Herdados de Training)
    @NotBlank(message = "O nome do treino é obrigatório.")
    private String name;

    @NotBlank(message = "A intensidade é obrigatória.")
    private String intensity;

    @NotBlank(message = "O objetivo do treino é obrigatório.")
    private String goal;

    @NotNull(message = "O ID do membro é obrigatório.")
    private Long gymMemberId;

    // CAMPO DISCRIMINADOR (Decide qual subclasse será instanciada)
    @NotBlank(message = "O tipo de treino é obrigatório (MUSCULACAO ou FUNCIONAL).")
    private String trainingType;

    // CAMPOS ESPECÍFICOS DE TREINO MUSCULAÇÃO (WeightTraining)
    private String trainingSplit; // Ex: Full Body, PPL, ABC (divisaoDoTreinamento)

    // CAMPOS ESPECÍFICOS DE TREINO FUNCIONAL (FunctionalTraining)
    private Boolean usesBodyWeightOnly; // Ex: true/false (usaApenasPesoCorpo)
    private Integer durationMinutes; // Ex: 45, 60 (duracaoMinutos)
}