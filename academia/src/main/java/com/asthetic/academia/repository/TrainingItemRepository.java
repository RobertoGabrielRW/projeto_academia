package com.asthetic.academia.repository;

import com.asthetic.academia.entitys.TrainingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrainingItemRepository extends JpaRepository<TrainingItem, Long> {

    List<TrainingItem> findByTrainingId(Long trainingId);
}
