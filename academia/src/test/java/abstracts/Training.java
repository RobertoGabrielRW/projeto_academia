package abstracts;

import entitys.GymMember;
import entitys.TrainingItem;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

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
