package com.asthetic.academia.entitys;

import com.asthetic.academia.abstracts.Training;
import jakarta.persistence.Column;
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
    @Column(length = 50)
    private String trainingSplit;


}