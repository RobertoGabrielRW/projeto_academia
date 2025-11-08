package entitys;

import abstracts.Training;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "TrainingItem")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TrainingItem extends Training {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer series;

    @Column(nullable = false)
    private Integer repeticoes;

    private Double cargaKg;

    private Integer descansoSegundos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treino_id", nullable = false)
    protected Training training;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "exercicio_id", nullable = false)
//    private Exercises exercicios;
}