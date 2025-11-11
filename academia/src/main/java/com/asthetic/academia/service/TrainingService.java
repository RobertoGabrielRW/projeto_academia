package com.asthetic.academia.service;

import com.asthetic.academia.abstracts.Training;
import com.asthetic.academia.dto.TrainingItemRequestDTO; // NOVO IMPORT
import com.asthetic.academia.dto.TrainingItemRequestDTO;
import com.asthetic.academia.dto.TrainingRequestDTO;
import com.asthetic.academia.entitys.WeightTraining; // Subclasse de treino com split
import com.asthetic.academia.entitys.FunctionalTraining; // Subclasse de treino funcional
import com.asthetic.academia.entitys.GymMember;
import com.asthetic.academia.entitys.TrainingItem; // NOVO IMPORT: Para os itens do treino
import com.asthetic.academia.entitys.Exercise; // NOVO IMPORT: Para a FK do item

import com.asthetic.academia.repository.TrainingRepository;
import com.asthetic.academia.repository.GymMemberRepository;
import com.asthetic.academia.repository.ExerciseRepository; // NOVO REPOSITÓRIO

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList; // NOVO IMPORT
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final GymMemberRepository gymMemberRepository;
    private final ExerciseRepository exerciseRepository; // INJEÇÃO DE DEPENDÊNCIA

    public TrainingService(
            TrainingRepository trainingRepository,
            GymMemberRepository gymMemberRepository,
            ExerciseRepository exerciseRepository) { // Construtor atualizado

        this.trainingRepository = trainingRepository;
        this.gymMemberRepository = gymMemberRepository;
        this.exerciseRepository = exerciseRepository;
    }

    // --- CREATE (ATUALIZADO PARA INCLUIR ITENS) ---
    @Transactional
    public Training create(TrainingRequestDTO dto) {

        // 1. Resolve a Chave Estrangeira do Membro
        GymMember member = gymMemberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new NoSuchElementException("Membro não encontrado..."));

        Training newTraining;

        // 2. Resolve a Herança (Instancia a subclasse correta)
        if ("WEIGHT_TRAINING".equalsIgnoreCase(dto.getTrainingType())) {
            WeightTraining t = new WeightTraining();
            t.setTrainingSplit(dto.getTrainingSplit());
            newTraining = t;

        } else if ("FUNCTIONAL_TRAINING".equalsIgnoreCase(dto.getTrainingType())) {
            FunctionalTraining t = new FunctionalTraining();
            t.setUsesBodyWeightOnly(dto.getUsesBodyWeightOnly());
            t.setDurationMinutes(dto.getDurationMinutes());
            newTraining = t;

        } else {
            throw new IllegalArgumentException("Unknown training type: " + dto.getTrainingType());
        }

        // 3. Mapeia campos comuns e associa o Membro
        newTraining.setName(dto.getName());
        newTraining.setIntensity(dto.getIntensity());
        newTraining.setGoal(dto.getGoal());
        newTraining.setGymMember(member);

        List<TrainingItem> trainingItemsList = new ArrayList<>();

        // 4. Mapeia a Lista de Itens (Relacionamento 1:N)
        if (dto.getTrainingItems() != null) {
            for (TrainingItemRequestDTO itemDto : dto.getTrainingItems()) {

                // Busca o Exercise (Chave Estrangeira)
                Exercise exercise = exerciseRepository.findById(itemDto.getExerciseId())
                        .orElseThrow(() -> new NoSuchElementException("Exercise not found with ID: " + itemDto.getExerciseId()));

                // Mapeamento DTO para TrainingItem Entity
                TrainingItem item = new TrainingItem();
                item.setSets(itemDto.getSets());
                item.setRepetitions(itemDto.getRepetitions());
                item.setLoadKg(itemDto.getLoadKg());
                item.setRestSeconds(itemDto.getRestSeconds());

                // Associa as duas FKs: Training e Exercise
                item.setTraining(newTraining); // Associa ao Treino que está sendo criado
                item.setExercise(exercise);

                trainingItemsList.add(item);
            }
        }

        // 5. Adiciona os itens ao Treino (o CascadeType.ALL em Training deve salvar eles)
        newTraining.setTrainingItems(trainingItemsList);

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

    // --- UPDATE (Mantido, mas lembre-se que atualizar itens seria mais complexo)
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

        // OBSERVAÇÃO: A atualização da lista de trainingItems exigiria lógica complexa de merge (limpar/adicionar).

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