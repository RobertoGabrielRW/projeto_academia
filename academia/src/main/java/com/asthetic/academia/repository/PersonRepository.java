package com.asthetic.academia.repository;

import com.asthetic.academia.abstracts.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * JPQL Complexa: Busca pessoas ativas em um CEP, garantindo que o complemento exista.
     * Corrigido para acessar o endereço via um objeto @Embedded (ex: 'address').
     */
    @Query("SELECT p FROM Person p " +
            "WHERE p.active = true " +
            "AND p.address.postalCode = :postalCode " +
            "AND p.address.complement IS NOT NULL " +
            "ORDER BY p.lastName, p.firstName DESC")
    List<Person> findActiveResidentsWithComplement(
            @Param("postalCode") String postalCode
    );
}