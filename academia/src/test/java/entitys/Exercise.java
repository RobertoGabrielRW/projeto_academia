package entitys;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

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


    @Column(name = "exercise_name", nullable = false, length = 100)
    private String exerciseName;

    @Column(name = "muscle_group", length = 50)
    private String muscleGroup;

    // --- RELATIONSHIPS ---

    // 1. Many Exercises belong to ONE Academy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy;

    // 2. One Exercise can be referenced by MANY Training Items (mappedBy = "exercise" na TrainingItem)
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingItem> trainingItems = new ArrayList<>();

}