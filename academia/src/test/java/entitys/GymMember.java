package entitys;

import abstracts.Person;
import com.sun.istack.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "GymMember")
public class GymMember extends Person {

    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollment;

}
