package dto;

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
public class GymMemberRequestDTO {

    @NotNull(message = "A matrícula é obrigatória.")
    private Long enrollment; // ID de Negócio

    @NotBlank(message = "O nome é obrigatório.")
    private String firstName;

    @NotBlank(message = "O sobrenome é obrigatório.")
    private String lastName;

    @Email(message = "Email inválido.")
    @NotBlank(message = "O email é obrigatório.")
    private String email;

    private String phone;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dateOfBirth;

    @NotBlank(message = "O gênero é obrigatório.")
    private String gender;


    private Long personalTrainerId;


    @NotBlank private String street;
    @NotBlank private String houseNumber;
    @NotBlank private String postalCode;
    private String complement;
}
