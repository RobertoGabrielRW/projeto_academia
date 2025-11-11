package com.asthetic.academia.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingItemRequestDTO {

    // CAMPOS DE DADOS DO EXERCÍCIO

    @NotNull(message = "O número de séries é obrigatório.")
    private Integer sets;

    @NotNull(message = "O número de repetições é obrigatório.")
    private Integer repetitions;

    private Double loadKg;

    private Integer restSeconds;

    @NotNull(message = "O ID do exercício (exerciseId) é obrigatório.")
    private Long exerciseId;
}
