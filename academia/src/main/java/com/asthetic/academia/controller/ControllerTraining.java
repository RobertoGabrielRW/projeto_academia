package com.asthetic.academia.controller;

import com.asthetic.academia.dto.TrainingRequestDTO;
import com.asthetic.academia.service.TrainingService;
import com.asthetic.academia.abstracts.Training;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/trainings") // URL base
public class ControllerTraining {

    private final TrainingService trainingService;

    public ControllerTraining(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    // --- 1. CREATE
    @PostMapping
    public ResponseEntity<Training> createTraining(@Valid @RequestBody TrainingRequestDTO trainingDTO) {


        try {

            Training createdTraining = trainingService.create(trainingDTO);
            return new ResponseEntity<>(createdTraining, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            // Este tratamento será melhorado com o GlobalExceptionHandler
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // --- 2. READ ALL
    @GetMapping
    public ResponseEntity<List<Training>> getAllTrainings() {
        List<Training> trainings = trainingService.findAll();
        return ResponseEntity.ok(trainings);
    }

    // --- 3. READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id) {
        return trainingService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // --- 4. UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Training> updateTraining(@PathVariable Long id, @Valid @RequestBody TrainingRequestDTO trainingDTO) {



        try {

            Training updatedTraining = trainingService.update(id, trainingDTO);
            return ResponseEntity.ok(updatedTraining);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        try {
            trainingService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}