package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademyRequestDTO {


    @NotBlank(message = "O CNPJ é obrigatório.")
    @Size(min = 18, max = 18, message = "O CNPJ deve ter 18 caracteres (formato XX.XXX.XXX/XXXX-XX).")
    private String corporateTaxId;

    @Email(message = "Email inválido.")
    @NotBlank(message = "O email é obrigatório.")
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String phone;



    @NotBlank
    private String street;

    @NotBlank
    private String houseNumber;

    @NotBlank
    private String postalCode;

    private String complement;
}
