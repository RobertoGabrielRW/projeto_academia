package com.asthetic.academia.controller;

import com.asthetic.academia.dto.PersonalTrainerRequestDTO;
import com.asthetic.academia.entitys.PersonalTrainer;
import com.asthetic.academia.service.PersonalTrainerService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/personal")
public class ControllerPersonal {

    private final PersonalTrainerService personalTrainerService;

    public ControllerPersonal(PersonalTrainerService personalTrainerService) {
        this.personalTrainerService = personalTrainerService;
    }

    // --- 1. CRIAR / CREATE (POST) ---

    @PostMapping
    public ResponseEntity<PersonalTrainer> createTrainer(@Valid @RequestBody PersonalTrainerRequestDTO trainerDTO) {
        try {
            PersonalTrainer createdTrainer = personalTrainerService.create(trainerDTO);
            return new ResponseEntity<>(createdTrainer, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            // Academy não encontrada
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // --- 2. LER TODOS / READ ALL (GET) ---

    @GetMapping
    public ResponseEntity<List<PersonalTrainer>> getAllTrainers() {
        List<PersonalTrainer> trainers = personalTrainerService.findAll();
        return ResponseEntity.ok(trainers);
    }

    // --- 3. LER POR ID / READ BY ID (GET) ---

    @GetMapping("/{id}")
    public ResponseEntity<PersonalTrainer> getTrainerById(@PathVariable Long id) {
        return personalTrainerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // --- 4. ATUALIZAR / UPDATE (PUT) ---

    @PutMapping("/{id}")
    public ResponseEntity<PersonalTrainer> updateTrainer(@PathVariable Long id, @Valid @RequestBody PersonalTrainerRequestDTO trainerDTO) {
        try {
            PersonalTrainer updatedTrainer = personalTrainerService.update(id, trainerDTO);
            return ResponseEntity.ok(updatedTrainer);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // --- 5. DELETAR / DELETE (DELETE) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        try {
            personalTrainerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *Retorna Personal Trainers cuja taxa horária está abaixo da média
     * de todos os trainers na mesma especialidade.
     */
    @GetMapping("/search/below-average-rate")
    public ResponseEntity<List<PersonalTrainer>> getTrainersBelowAverageRate() {
        List<PersonalTrainer> trainers = personalTrainerService.findTrainersBelowAverageRate();

        if (trainers.isEmpty()) {
            // Retorna 404 se não houver resultados que atendam à condição
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trainers);
    }
}