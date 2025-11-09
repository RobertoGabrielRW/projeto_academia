package entitys;

import abstracts.Person; // Assume que Person está no pacote 'abstracts'
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "employee")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Person {


    @Column(name = "employee_id", unique = true)
    private Long employeeId;


    @Column(name = "area_of_expertise", length = 100)
    private String areaOfExpertise;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy;


}