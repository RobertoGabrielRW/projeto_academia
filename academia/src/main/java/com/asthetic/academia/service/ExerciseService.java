package com.asthetic.academia.service;

import com.asthetic.academia.entitys.Exercise;
import com.asthetic.academia.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    // --- CREATE
    @Transactional
    public Exercise create(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    // --- READ
    @Transactional(readOnly = true)
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Exercise> findById(Long id) {
        return exerciseRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Exercise> findByMuscleGroup(String muscleGroup) {
        return exerciseRepository.findByMuscleGroup(muscleGroup);
    }

    // --- UPDATE
    @Transactional
    public Exercise update(Long id, Exercise updatedData) {
        Exercise existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Exercício não encontrado com ID: " + id));

        existingExercise.setExerciseName(updatedData.getExerciseName());
        existingExercise.setMuscleGroup(updatedData.getMuscleGroup());

        return exerciseRepository.save(existingExercise);
    }

    // --- DELETE
    @Transactional
    public void deleteById(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new NoSuchElementException("Exercício não encontrado com ID: " + id);
        }
        exerciseRepository.deleteById(id);
    }
}