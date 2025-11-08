package entitys;

import abstracts.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "personal_trainer")
@PrimaryKeyJoinColumn(name = "person_id")
@NoArgsConstructor
@AllArgsConstructor
public class PersonalTrainner extends Person {



    @Column(length = 50)
    private String certification;

    private Integer yearsOfExperience;
    private Double hourlyRate;
    private Double rating;
    private String specialties;
    private Boolean available;
    private String scheduleNotes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy;


}