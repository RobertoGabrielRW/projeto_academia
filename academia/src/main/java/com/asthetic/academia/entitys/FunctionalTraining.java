
package com.asthetic.academia.entitys;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.asthetic.academia.abstracts.Training;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@DiscriminatorValue("FUNCTIONAL_TRAINING")
public class FunctionalTraining extends Training {


    private Boolean usesBodyWeightOnly;
    private Integer durationMinutes;


}