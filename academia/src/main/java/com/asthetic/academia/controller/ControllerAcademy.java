package com.asthetic.academia.controller;

import com.asthetic.academia.dto.AcademyRequestDTO;
import com.asthetic.academia.entitys.Academy;
import com.asthetic.academia.service.AcademyService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/academies")
public class ControllerAcademy {

    private final AcademyService academyService;

    public ControllerAcademy(AcademyService academyService) {
        this.academyService = academyService;
    }


    @PostMapping
    public ResponseEntity<Academy> createAcademy(@Valid @RequestBody AcademyRequestDTO academyDTO) {
        try {
            Academy createdAcademy = academyService.create(academyDTO);
            return new ResponseEntity<>(createdAcademy, HttpStatus.CREATED);
        } catch (Exception e) {
            // Erro de validação de campo ou violação de restrição do banco (ex: CNPJ UNIQUE)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<List<Academy>> getAllAcademies() {
        List<Academy> academies = academyService.findAll();
        return ResponseEntity.ok(academies);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Academy> getAcademyById(@PathVariable Long id) {
        return academyService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Academy> updateAcademy(@PathVariable Long id, @Valid @RequestBody AcademyRequestDTO academyDTO) {
        try {
            Academy updatedAcademy = academyService.update(id, academyDTO);
            return ResponseEntity.ok(updatedAcademy);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcademy(@PathVariable Long id) {
        try {
            academyService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}