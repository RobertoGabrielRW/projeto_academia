package com.asthetic.academia.service;

import com.asthetic.academia.abstracts.Training;
import com.asthetic.academia.dto.TrainingRequestDTO;
import com.asthetic.academia.repository.TrainingRepository;
import com.asthetic.academia.repository.GymMemberRepository;
import com.asthetic.academia.entitys.WeightTraining; // Nome em Inglês
import com.asthetic.academia.entitys.FunctionalTraining; // Nome em Inglês
import com.asthetic.academia.entitys.GymMember;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final GymMemberRepository gymMemberRepository;

    public TrainingService(TrainingRepository trainingRepository, GymMemberRepository gymMemberRepository) {
        this.trainingRepository = trainingRepository;
        this.gymMemberRepository = gymMemberRepository;
    }

    // --- CREATE (Com nomes em Inglês) ---
    @Transactional
    public Training create(TrainingRequestDTO dto) {

        // 1. Valida se o membro existe
        GymMember member = gymMemberRepository.findById(dto.getGymMemberId())
                .orElseThrow(() -> new NoSuchElementException("Member not found for Training creation."));

        Training newTraining;

        // 2. LÓGICA DE DECISÃO DE HERANÇA (Tipos em Inglês)
        if ("WEIGHT_TRAINING".equalsIgnoreCase(dto.getTrainingType())) {
            WeightTraining t = new WeightTraining();
            // Mapeamento dos atributos em Inglês
            t.setTrainingSplit(dto.getTrainingSplit());
            newTraining = t;

        } else if ("FUNCTIONAL_TRAINING".equalsIgnoreCase(dto.getTrainingType())) {
            FunctionalTraining t = new FunctionalTraining();
            // Mapeamento dos atributos em Inglês
            t.setUsesBodyWeightOnly(dto.getUsesBodyWeightOnly());
            t.setDurationMinutes(dto.getDurationMinutes());
            newTraining = t;

        } else {
            throw new IllegalArgumentException("Unknown training type: " + dto.getTrainingType());
        }


        newTraining.setName(dto.getName());
        newTraining.setIntensity(dto.getIntensity());
        newTraining.setGoal(dto.getGoal());
        newTraining.setGymMember(member);

        return trainingRepository.save(newTraining);
    }

    // --- READ (Busca) ---
    @Transactional(readOnly = true)
    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Training> findById(Long id) {
        return trainingRepository.findById(id);
    }

    // --- UPDATE
    @Transactional
    public Training update(Long id, TrainingRequestDTO dto) {
        Training existingTraining = trainingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Training not found with ID: " + id));


        existingTraining.setName(dto.getName());
        existingTraining.setIntensity(dto.getIntensity());
        existingTraining.setGoal(dto.getGoal());


        if (existingTraining instanceof WeightTraining && "WEIGHT_TRAINING".equalsIgnoreCase(dto.getTrainingType())) {
            WeightTraining wt = (WeightTraining) existingTraining;
            wt.setTrainingSplit(dto.getTrainingSplit());
        } else if (existingTraining instanceof FunctionalTraining && "FUNCTIONAL_TRAINING".equalsIgnoreCase(dto.getTrainingType())) {
            FunctionalTraining ft = (FunctionalTraining) existingTraining;
            ft.setUsesBodyWeightOnly(dto.getUsesBodyWeightOnly());
            ft.setDurationMinutes(dto.getDurationMinutes());
        }

        return trainingRepository.save(existingTraining);
    }

    // --- DELETE (Deleção) ---
    @Transactional
    public void deleteById(Long id) {
        if (!trainingRepository.existsById(id)) {
            throw new NoSuchElementException("Training not found with ID: " + id);
        }
        trainingRepository.deleteById(id);
    }
}