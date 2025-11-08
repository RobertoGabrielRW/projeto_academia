package abstracts;

import entitys.Address; // Classe @Embeddable para composição do endereço
import jakarta.persistence.*;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.*; // Importa as anotações do Lombok

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
@Data // Gera automaticamente getters, setters, equals, hashCode e toString
@NoArgsConstructor // Construtor vazio (necessário para JPA)
@AllArgsConstructor // Construtor com todos os atributos
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String gender;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    @Embedded
    private Address address;
}