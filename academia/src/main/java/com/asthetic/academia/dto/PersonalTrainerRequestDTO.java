package com.asthetic.academia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalTrainerRequestDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    private String phone;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    private String gender;

    @NotBlank(message = "A certificação é obrigatória.")
    private String certification;

    @NotNull(message = "Anos de experiência são obrigatórios.")
    private Integer yearsOfExperience;

    @NotNull(message = "A taxa horária é obrigatória.")
    private Double hourlyRate;

    private String specialties;


    @NotNull(message = "ID da Academia é obrigatório.")
    private Long academyId;


    @NotBlank private String street;
    @NotBlank private String houseNumber;
    @NotBlank private String postalCode;
    private String complement;
}
