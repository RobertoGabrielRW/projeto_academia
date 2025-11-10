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


    @NotBlank(message = "O nome do treino é obrigatório.")
    private String name;

    @NotBlank(message = "A intensidade é obrigatória.")
    private String intensity;

    @NotBlank(message = "O objetivo do treino é obrigatório.")
    private String goal;


    @NotNull(message = "O ID do membro é obrigatório.")
    private Long gymMemberId;


    @NotBlank(message = "O tipo de treino é obrigatório (MUSCULACAO ou FUNCIONAL).")
    private String trainingType;

    private String divisaoDoTreinamento;

    private Boolean usaApenasPesoCorpo;
    private Integer duracaoMinutos;

}
