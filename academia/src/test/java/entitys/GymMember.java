package entitys;

import abstracts.Person;
import abstracts.Training;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "gym_member")
@PrimaryKeyJoinColumn(name = "person_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GymMember extends Person {


    @Column(name = "enrollment", unique = true, nullable = false)
    private Long enrollment;


    @OneToMany(mappedBy = "gymMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Training> trainings = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_trainer_id")
    private PersonalTrainner personalTrainer;
}