package com.asthetic.academia.repository;

import com.asthetic.academia.abstracts.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {


    List<Training> findByGymMemberId(Long gymMemberId);
}
