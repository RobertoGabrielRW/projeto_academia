package abstracts;

import entitys.Aluno;
import entitys.ItemTreino;
import jakarta.persistence.*;

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
    private Aluno aluno;


    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemTreino> itensTreino = new ArrayList<>();

    public Training() {

    }

    public Training(Long id, String name, String intensity, Double estimatedCalories, String goal, Aluno aluno) {
        this.id = id;
        this.name = name;
        this.intensity = intensity;
        this.estimatedCalories = estimatedCalories;
        this.goal = goal;
        this.aluno = aluno;
    }
    // GATTERS
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getIntensity() {
        return intensity;
    }
    public Double getEstimatedCalories () {
        return estimatedCalories;
    }
    public String getGoal() {
        return goal;
    }
    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
    public void setEstimatedCalories(Double estimatedCalories) {
        this.estimatedCalories = estimatedCalories;
    }
    public void setGoal(String goal) {
        this.goal = goal;
    }
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<ItemTreino> getItensTreino() {
        return itensTreino;
    }

    public void setItensTreino(List<ItemTreino> itensTreino) {
        this.itensTreino = itensTreino;
    }
}
