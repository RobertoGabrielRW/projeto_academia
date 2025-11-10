package com.asthetic.academia.service;

import com.asthetic.academia.entitys.Employee;
import com.asthetic.academia.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // --- CREATE
    @Transactional
    public Employee create(Employee employee) {
        if (employeeRepository.findByEmployeeId(employee.getEmployeeId()) != null) {
            throw new IllegalStateException("ID de Funcionário (Negócio) já cadastrado.");
        }
        return employeeRepository.save(employee);
    }

    // --- READ
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    // --- UPDATE
    @Transactional
    public Employee update(Long id, Employee updatedData) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionário não encontrado com ID: " + id));

        existingEmployee.setAreaOfExpertise(updatedData.getAreaOfExpertise());
        existingEmployee.setActive(updatedData.isActive());

        existingEmployee.setFirstName(updatedData.getFirstName());
        existingEmployee.setEmail(updatedData.getEmail());

        return employeeRepository.save(existingEmployee);
    }

    // --- DELETE
    @Transactional
    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new NoSuchElementException("Funcionário não encontrado com ID: " + id);
        }
        employeeRepository.deleteById(id);
    }
}