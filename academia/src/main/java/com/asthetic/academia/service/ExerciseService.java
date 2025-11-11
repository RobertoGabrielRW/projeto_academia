package com.asthetic.academia.service;

import com.asthetic.academia.dto.ExerciseRequestDTO; // NOVO IMPORT
import com.asthetic.academia.entitys.Exercise;
import com.asthetic.academia.entitys.Academy; // NOVO IMPORT
import com.asthetic.academia.repository.ExerciseRepository;
import com.asthetic.academia.repository.AcademyRepository; // NOVO IMPORT
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final AcademyRepository academyRepository; // NOVO REPOSITÓRIO

    public ExerciseService(
            ExerciseRepository exerciseRepository,
            AcademyRepository academyRepository) { // Construtor atualizado
        this.exerciseRepository = exerciseRepository;
        this.academyRepository = academyRepository;
    }

    // --- CREATE (Corrigido para usar DTO e associar Academy)
    @Transactional
    public Exercise create(ExerciseRequestDTO dto) {
        // 1. Validação da Chave Estrangeira (Academy)
        Academy academy = academyRepository.findById(dto.getAcademyId())
                .orElseThrow(() -> new NoSuchElementException("Academy não encontrada com ID: " + dto.getAcademyId()));

        // 2. Mapeamento DTO -> Entidade
        Exercise exercise = new Exercise();
        exercise.setExerciseName(dto.getName());
        exercise.setDescription(dto.getDescription());
        exercise.setMuscleGroup(dto.getMuscleGroup());

        // 3. Associação
        exercise.setAcademy(academy); // Associa o objeto Academy

        return exerciseRepository.save(exercise);
    }

    // --- READ (Mantidos)
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

    // --- UPDATE (Corrigido para usar DTO e ser mais seguro)
    @Transactional
    public Exercise update(Long id, ExerciseRequestDTO dto) {
        Exercise existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Exercício não encontrado com ID: " + id));

        // Atualiza campos de dados
        existingExercise.setExerciseName(dto.getName());
        existingExercise.setDescription(dto.getDescription());
        existingExercise.setMuscleGroup(dto.getMuscleGroup());

        // Opcional: Se o ID da Academia mudar, busca e associa
        if (!existingExercise.getAcademy().getIdAcademia().equals(dto.getAcademyId())) {
            Academy newAcademy = academyRepository.findById(dto.getAcademyId())
                    .orElseThrow(() -> new NoSuchElementException("Nova Academy não encontrada com ID: " + dto.getAcademyId()));
            existingExercise.setAcademy(newAcademy);
        }

        return exerciseRepository.save(existingExercise);
    }

    // --- DELETE (Mantido)
    @Transactional
    public void deleteById(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new NoSuchElementException("Exercício não encontrado com ID: " + id);
        }
        exerciseRepository.deleteById(id);
    }
}