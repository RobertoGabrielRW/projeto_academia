package com.asthetic.academia.repository;

import com.asthetic.academia.entitys.PersonalTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Long> {

    List<PersonalTrainer> findBySpecialtiesContainingAndAvailableTrue(String specialty);
}
