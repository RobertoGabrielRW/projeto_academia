package abstracts; // (Recomendado mover para 'entitys' ou 'model')

import entitys.GymMember;
import entitys.TrainingItem;
import jakarta.persistence.*;
import lombok.Getter; // 1. Use @Getter
import lombok.Setter; // 2. Use @Setter
import java.util.ArrayList;
import java.util.List;

@Entity // 3. MUDANÇA PRINCIPAL: de @MappedSuperclass para @Entity
@Table(name = "training") // 4. Define o nome da tabela única
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 5. Define a estratégia de herança
@DiscriminatorColumn(name = "tipo_treino", discriminatorType = DiscriminatorType.STRING) // 6. Coluna que diz o tipo
@Getter // 7. Adicionado (seguro para JPA)
@Setter // 8. Adicionado (seguro para JPA)
public abstract class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String intensity;
    private Double estimatedCalories;
    private String goal;

    // 9. CORREÇÃO DE LÓGICA:
    // O relacionamento deve ser o inverso: Um GymMember (Aluno) tem MUITOS Treinos.
    // O @ManyToOne deve estar em Training, e o @OneToMany em GymMember.
    // O seu código atual está correto.
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
