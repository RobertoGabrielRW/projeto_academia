package com.asthetic.academia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GymMemberRequestDTO {

    // CAMPOS HERDADOS DE PERSON
    @NotBlank(message = "O primeiro nome é obrigatório.")
    private String firstName;

    @NotBlank(message = "O sobrenome é obrigatório.")
    private String lastName;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotBlank(message = "O gênero é obrigatório.")
    private String gender;

    // CAMPOS DE CONTATO (ADICIONADOS PARA CORRIGIR O ERRO DE COMPILAÇÃO)
    @Email(message = "Email inválido.")
    @NotBlank(message = "O email é obrigatório.")
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String phone;

    // CAMPOS DE ENDEREÇO (ADICIONADOS PARA CORRIGIR O ERRO 500 DE house_number)
    @NotBlank(message = "A rua é obrigatória.")
    private String street;

    @NotBlank(message = "O número da casa é obrigatório.")
    private String houseNumber;

    @NotBlank(message = "O CEP é obrigatório.")
    private String postalCode;

    private String complement; // Opcional

    // CAMPO ESPECÍFICO DE GYM MEMBER
    @NotBlank(message = "O número de matrícula é obrigatório.")
    private String enrollment;

    // CAMPO DE RELACIONAMENTO (Chave estrangeira)
    @NotNull(message = "O ID da Academia é obrigatório.")
    private Long academyId;

    @NotNull(message = "O ID do Personal Trainer é obrigatório.")
    private Long personalTrainerId;
}