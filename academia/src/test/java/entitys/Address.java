package entitys;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int houseNumber;

    @Column(nullable = false)
    private double postalCode;

    @Column
    private String complement;
}
