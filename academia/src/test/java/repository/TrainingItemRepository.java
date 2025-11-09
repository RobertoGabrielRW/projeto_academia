package repository;

import entitys.TrainingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrainingItemRepository extends JpaRepository<TrainingItem, Long> {

    List<TrainingItem> findByTrainingId(Long trainingId);
}
