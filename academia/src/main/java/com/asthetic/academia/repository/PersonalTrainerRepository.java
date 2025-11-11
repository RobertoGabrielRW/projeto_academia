package com.asthetic.academia.repository;

import com.asthetic.academia.entitys.PersonalTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Long> {

    List<PersonalTrainer> findBySpecialtiesContainingAndAvailableTrue(String specialty);

    /**
     * JPQL Complexa: Busca Personal Trainers cuja taxa horária (hourlyRate)
     * esteja abaixo da média das taxas de todos os trainers que compartilham
     * o mesmo *primeiro* valor de especialidade (simplificação para JPQL).
     */
    @Query("SELECT pt FROM PersonalTrainer pt " +
            "WHERE pt.hourlyRate < (" +
            "    SELECT AVG(p.hourlyRate) FROM PersonalTrainer p " +
            "    WHERE p.specialties = pt.specialties" + // Causa provável de erro se for Collection
            ") " +
            "ORDER BY pt.specialties, pt.hourlyRate ASC")
    List<PersonalTrainer> findTrainersBelowAverageRateBySpecialty();;
}