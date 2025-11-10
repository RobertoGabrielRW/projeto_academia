package com.asthetic.academia.repository;

import com.asthetic.academia.entitys.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long> {


    Academy findByCorporateTaxId(String corporateTaxId);
}
