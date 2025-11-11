package com.asthetic.academia.controller;

import com.asthetic.academia.dto.ExerciseRequestDTO;
import com.asthetic.academia.entitys.Exercise;
import com.asthetic.academia.service.ExerciseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/exercises") // URL base: http://localhost:8080/exercises
public class ControllerExercise {

    private final ExerciseService exerciseService;

    // Injeção de Dependência via Construtor
    public ControllerExercise(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    // --- 1. CREATE (POST) ---
    @PostMapping
    public ResponseEntity<Exercise> createExercise(@Valid @RequestBody ExerciseRequestDTO exerciseDTO) {
        try {
            // Chama o serviço para criar, que valida o AcademyId
            Exercise createdExercise = exerciseService.create(exerciseDTO);
            return new ResponseEntity<>(createdExercise, HttpStatus.CREATED); // 201 Created
        } catch (NoSuchElementException e) {
            // Exceção de negócio (AcademyId não encontrado)
            // Retorna 400 Bad Request, indicando que o dado enviado é inválido
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // --- 2. READ ALL (GET) ---
    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAll();
        return ResponseEntity.ok(exercises); // 200 OK
    }

    // --- 3. READ BY ID (GET) ---
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        return exerciseService.findById(id)
                .map(ResponseEntity::ok) // 200 OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
    }

    // --- 4. UPDATE (PUT) ---
    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @Valid @RequestBody ExerciseRequestDTO exerciseDTO) {
        try {
            Exercise updatedExercise = exerciseService.update(id, exerciseDTO);
            return ResponseEntity.ok(updatedExercise); // 200 OK
        } catch (NoSuchElementException e) {
            // Pode ser o ID do Exercício ou o novo ID da Academia
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // --- 5. DELETE (DELETE) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        try {
            exerciseService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
