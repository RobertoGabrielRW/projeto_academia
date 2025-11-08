package entitys;
import jakarta.persistence.*;
import jakarta.persistence.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import abstracts.Person;
import org.springframework.data.annotation.Id;


@Entity
@Table(name = "PersonalTrainnerl")

public class PersonalTrainner extends Person {


    @jakarta.persistence.Id
    private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonalTrainnerl;


    private String certification;

    private Integer yearsOfExperience; // anos de experiência
    private Double hourlyRate;
    private Double rating;
    private String specialties;
    private Boolean available;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Academy address;
    private String scheduleNotes;

    public Academy getAddress() {
        return address;
    }

    public void setAddress(Academy address) {
        this.address = address;
    }
}

