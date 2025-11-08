package entitys;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "exercise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- ATTRIBUTES ---

    // Nome do exercício
    @Column(name = "exercise_name", nullable = false, length = 100)
    private String exerciseName;

    // Grupo muscular primário
    @Column(name = "muscle_group", length = 50)
    private String muscleGroup;

    // --- RELATIONSHIPS ---

    // 1. Many Exercises belong to ONE Academy (Muitos para Um)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy; // Dependência da próxima classe (Academy)

    // 2. One Exercise can be referenced by MANY Training Items (Um para Muitos)
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingItem> trainingItems = new ArrayList<>();

}
