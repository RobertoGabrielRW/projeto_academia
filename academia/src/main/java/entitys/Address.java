package entitys;

import jakarta.persistence.Embeddable; // 1. IMPORTAÇÃO CORRETA
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {


    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "postal_code", nullable = false, length = 15)
    private String postalCode;

    @Column(name = "complement")
    private String complement;
}