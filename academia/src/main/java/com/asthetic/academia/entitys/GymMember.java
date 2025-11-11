package com.asthetic.academia.entitys;

import com.asthetic.academia.abstracts.Person;
import com.asthetic.academia.abstracts.Training;
import com.asthetic.academia.entitys.PersonalTrainer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "gym_member")
@PrimaryKeyJoinColumn(name = "person_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GymMember extends Person {


    @Column(name = "enrollment", unique = true, nullable = false)
    private String enrollment;

    @ManyToOne
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy;


    @OneToMany(mappedBy = "gymMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Training> trainings = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "personal_trainer_id",nullable = true)
    private PersonalTrainer personalTrainer;
}