package com.asthetic.academia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymMemberRequestDTO {

    // CAMPOS HERDADOS DE PERSON
    @NotBlank(message = "O primeiro nome é obrigatório.")
    private String firstName;

    @NotBlank(message = "O sobrenome é obrigatório.")
    private String lastName;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dateOfBirth;

    @NotBlank(message = "O gênero é obrigatório.")
    private String gender;

    // CAMPO ESPECÍFICO DE GYM MEMBER
    @NotBlank(message = "O número de matrícula é obrigatório.")
    private String enrollment;

    // CAMPO DE RELACIONAMENTO (Chave estrangeira)
    @NotNull(message = "O ID da Academia é obrigatório.")
    private Long academyId;

    @NotNull(message = "O ID do Personal Trainer é obrigatório.")
    private Long personalTrainerId;
}