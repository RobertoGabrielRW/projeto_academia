package com.asthetic.academia.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAcademia;

    @Column(name = "corporate_tax_id", nullable = false, unique = true, length = 18)
    private String corporateTaxId;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;


    @Embedded
    private Address address;

    @OneToMany(mappedBy = "academy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> exercises = new ArrayList<>();

    @OneToMany(mappedBy = "academy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "academy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalTrainer> personalTrainers = new ArrayList<>(); // 4. Corrigido o nome da classe
}