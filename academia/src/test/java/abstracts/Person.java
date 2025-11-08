package abstracts;

import com.sun.istack.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

//classe abstrata que representa uma pessoa no sistema da academia
//contém atributos comuns usados por membros, treinadores e funcionários
@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private boolean active = true;

    public Person() {
    }
    //construtor completo da classe Person
    public Person(Long id, String firstName, String lastName, String email, String phone, LocalDate dateOfBirth, String gender, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.active = active;
    }
}
