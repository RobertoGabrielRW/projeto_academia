package entitys;

import jakarta.persistence.*;
import lombok.Getter; // 1. Usar @Getter
import lombok.Setter; // 2. Usar @Setter
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academy") // snake_case
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "corporate_tax_id", nullable = false, unique = true, length = 18)
    private String corporateTaxId;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;

    // 3. CORREÇÃO CRÍTICA: Se Address for @Embeddable, use @Embedded, não @OneToOne
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "academy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> exercises = new ArrayList<>();

    @OneToMany(mappedBy = "academy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "academy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalTrainner> personalTrainers = new ArrayList<>(); // 4. Corrigido o nome da classe
}