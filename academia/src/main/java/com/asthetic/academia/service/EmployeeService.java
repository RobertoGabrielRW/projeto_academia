package com.asthetic.academia.service;

import com.asthetic.academia.dto.EmployeeRequestDTO;
import com.asthetic.academia.entitys.Employee;
import com.asthetic.academia.entitys.Academy;
import com.asthetic.academia.abstracts.Person;
import com.asthetic.academia.entitys.Address;
import com.asthetic.academia.repository.EmployeeRepository;
import com.asthetic.academia.repository.AcademyRepository;
import com.asthetic.academia.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AcademyRepository academyRepository;
    private final PersonRepository personRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            AcademyRepository academyRepository,
            PersonRepository personRepository) {
        this.employeeRepository = employeeRepository;
        this.academyRepository = academyRepository;
        this.personRepository = personRepository;
    }

    // --- CREATE
    @Transactional
    public Employee create(EmployeeRequestDTO dto) {

        // 1. Validação de Unicidade
        if (employeeRepository.existsByEmployeeId(dto.getEmployeeId())) {
            throw new IllegalStateException("ID de Funcionário (Negócio) já cadastrado: " + dto.getEmployeeId());
        }

        Academy academy = academyRepository.findById(dto.getAcademyId())
                .orElseThrow(() -> new NoSuchElementException("Academia não encontrada com ID: " + dto.getAcademyId()));


        Employee newEmployee = new Employee();


        Address employeeAddress = new Address();
        employeeAddress.setStreet(dto.getStreet());
        employeeAddress.setHouseNumber(dto.getHouseNumber());
        employeeAddress.setComplement(dto.getComplement());
        employeeAddress.setPostalCode(dto.getPostalCode());


        newEmployee.setFirstName(dto.getFirstName());
        newEmployee.setLastName(dto.getLastName());
        newEmployee.setEmail(dto.getEmail());
        newEmployee.setPhone(dto.getPhone());
        newEmployee.setDateOfBirth(dto.getDateOfBirth());
        newEmployee.setGender(dto.getGender());
        newEmployee.setActive(dto.isActive());

        // ASSOCIA O OBJETO ADDRESS
        newEmployee.setAddress(employeeAddress);

        // Mapeamento: CAMPOS DA CLASSE FILHA (Employee)
        newEmployee.setEmployeeId(dto.getEmployeeId());
        newEmployee.setAreaOfExpertise(dto.getAreaOfExpertise());

        // Associa a FK
        newEmployee.setAcademy(academy);

        return employeeRepository.save(newEmployee);
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
    public Employee update(Long id, EmployeeRequestDTO dto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionário não encontrado com ID: " + id));

        Address existingAddress = existingEmployee.getAddress();

        existingEmployee.setFirstName(dto.getFirstName());
        existingEmployee.setLastName(dto.getLastName());
        existingEmployee.setEmail(dto.getEmail());
        existingEmployee.setPhone(dto.getPhone());
        existingEmployee.setDateOfBirth(dto.getDateOfBirth());
        existingEmployee.setGender(dto.getGender());
        existingEmployee.setActive(dto.isActive());

        // Atualização de Endereço através do objeto Address
        existingAddress.setStreet(dto.getStreet());
        existingAddress.setHouseNumber(dto.getHouseNumber());
        existingAddress.setComplement(dto.getComplement());
        existingAddress.setPostalCode(dto.getPostalCode());

        // Atualização de Campos Próprios
        existingEmployee.setAreaOfExpertise(dto.getAreaOfExpertise());

        return employeeRepository.save(existingEmployee);
    }

    @Transactional(readOnly = true)
    public List<Person> findActiveResidents(String postalCode) {
        return personRepository.findActiveResidentsWithComplement(postalCode);
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