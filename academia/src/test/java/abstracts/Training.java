package abstracts;

import entitys.GymMember;
import entitys.TrainingItem;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String intensity;
    private Double estimatedCalories;
    private String goal;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private GymMember gymMember;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingItem> trainingItems = new ArrayList<>();
}