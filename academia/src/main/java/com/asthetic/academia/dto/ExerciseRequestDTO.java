package com.asthetic.academia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRequestDTO {

    @NotBlank(message = "O nome do exercício é obrigatório.")
    private String name;

    private String description;

    @NotBlank(message = "O grupo muscular é obrigatório.")
    private String muscleGroup;

    @NotNull(message = "O ID da Academia é obrigatório.")
    private Long academyId;
}
