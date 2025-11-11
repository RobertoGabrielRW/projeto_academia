package com.asthetic.academia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Data
@Getter
@Setter
public class EmployeeRequestDTO {

    // --- CAMPOS DA CLASSE FILHA (Employee) ---
    @NotNull(message = "O ID do funcionário é obrigatório.")
    private Long employeeId;

    @NotBlank(message = "A área de especialidade é obrigatória.")
    private String areaOfExpertise;

    @NotNull(message = "O ID da academia é obrigatório.")
    private Long academyId;

    // --- CAMPOS DA CLASSE PAI (Person) ---
    @NotBlank(message = "O primeiro nome é obrigatório.")
    private String firstName;

    @NotBlank(message = "O último nome é obrigatório.")
    private String lastName;

    @Email(message = "Email inválido.")
    @NotBlank(message = "O email é obrigatório.")
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String phone;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dateOfBirth;

    @NotBlank(message = "O gênero é obrigatório.")
    private String gender;

    // --- CAMPOS DE ENDEREÇO HERDADOS DE PERSON ---
    @NotBlank(message = "O nome da rua é obrigatório.")
    private String street;

    @NotBlank(message = "O número da casa é obrigatório.")
    private String houseNumber;

    // Complemento pode ser opcional
    private String complement;

    @NotBlank(message = "O CEP é obrigatório.")
    private String postalCode;

    // Campo 'active' (status)
    private boolean active;
}