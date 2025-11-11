package com.asthetic.academia.controller;

import com.asthetic.academia.dto.EmployeeRequestDTO;
import com.asthetic.academia.entitys.Employee;
import com.asthetic.academia.abstracts.Person;
import com.asthetic.academia.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
public class ControllerEmployee {

    private final EmployeeService employeeService;

    public ControllerEmployee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // --- 1. CREATE (POST /employees) ---
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
        // O @Valid garante que as anotações @NotBlank e @NotNull no DTO sejam verificadas.
        Employee newEmployee = employeeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    // --- 2. READ ALL (GET /employees) ---
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    // --- 3. READ BY ID (GET /employees/{id}) ---
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);

        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- 4. UPDATE (PUT /employees/{id}) ---
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO dto) {
        try {
            // Reutilizamos o método update que criamos no serviço
            Employee updatedEmployee = employeeService.update(id, dto);
            return ResponseEntity.ok(updatedEmployee);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // --- 5. DELETE (DELETE /employees/{id}) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Busca Pessoas (incluindo Employees) ativas em um CEP com complemento de endereço.
     * Chama o método do EmployeeService que utiliza o PersonRepository.
     */
    @GetMapping("/search/residents")
    public ResponseEntity<List<Person>> getActiveResidentsByPostalCode(
            @RequestParam("cep") String postalCode) {

        // A chamada vai para o EmployeeService, que executa a consulta JPQL no PersonRepository
        List<Person> residents = employeeService.findActiveResidents(postalCode);

        if (residents.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // Retorna a lista de Pessoas que atendem aos critérios (Pode ser Employee, PT, ou Membro)
        return ResponseEntity.ok(residents);
    }
}