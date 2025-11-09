package entitys;

import abstracts.Training;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@DiscriminatorValue("FUNCIONAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FunctionalTraining extends Training {

    private Boolean usesBodyweightOnly;
    private Integer durationMinutes;


}