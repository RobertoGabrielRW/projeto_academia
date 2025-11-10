package com.asthetic.academia.entitys;

import com.asthetic.academia.abstracts.Training;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;

@Entity
@DiscriminatorValue("WEIGHT_TRAINING")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeightTraining extends Training {


    @Column(length = 50)
    private String trainingSplit;


}
