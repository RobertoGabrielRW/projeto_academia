package abstracts; // (Recomendado mover para 'entitys' ou 'model')

import entitys.GymMember;
import entitys.TrainingItem;
import jakarta.persistence.*;
import lombok.Getter; // 1. Use @Getter
import lombok.Setter; // 2. Use @Setter
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "training")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_treino", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
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
    private List<TrainingItem> itensTreino = new ArrayList<>();

    public Training() {

    }

    public Training(Long id, String name, String intensity, Double estimatedCalories, String goal, GymMember gymMember) {
        this.id = id;
        this.name = name;
        this.intensity = intensity;
        this.estimatedCalories = estimatedCalories;
        this.goal = goal;
        this.gymMember = gymMember;
    }

}
