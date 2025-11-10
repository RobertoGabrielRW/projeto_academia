package com.asthetic.academia.repository;

import com.asthetic.academia.entitys.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByAreaOfExpertise(String areaOfExpertise);

    Employee findByEmployeeId(Long employeeId);
}