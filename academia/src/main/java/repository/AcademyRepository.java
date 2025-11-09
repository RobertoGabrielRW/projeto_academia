package repository;

import entitys.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long> {


    Academy findByCorporateTaxId(String corporateTaxId);
}
