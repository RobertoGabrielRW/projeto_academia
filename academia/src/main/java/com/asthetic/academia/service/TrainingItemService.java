package com.asthetic.academia.service;

import com.asthetic.academia.entitys.TrainingItem;
import com.asthetic.academia.repository.TrainingItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class TrainingItemService {

    private final TrainingItemRepository trainingItemRepository;

    public TrainingItemService(TrainingItemRepository trainingItemRepository) {
        this.trainingItemRepository = trainingItemRepository;
    }

    // --- CREATE
    @Transactional
    public TrainingItem add(TrainingItem item) {
        if (item.getSets() <= 0 || item.getRepetitions() <= 0) {
            throw new IllegalArgumentException("Séries e repetições devem ser maiores que zero.");
        }
        return trainingItemRepository.save(item);
    }

    // --- READ
    @Transactional(readOnly = true)
    public Optional<TrainingItem> findById(Long id) {
        return trainingItemRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<TrainingItem> findItemsByTraining(Long trainingId) {
        return trainingItemRepository.findByTrainingId(trainingId);
    }

    // --- UPDATE
    @Transactional
    public TrainingItem update(Long id, TrainingItem updatedData) {
        TrainingItem existingItem = trainingItemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item de Treino não encontrado com ID: " + id));

        existingItem.setSets(updatedData.getSets());
        existingItem.setRepetitions(updatedData.getRepetitions());
        existingItem.setLoadKg(updatedData.getLoadKg());
        existingItem.setRestSeconds(updatedData.getRestSeconds());

        return trainingItemRepository.save(existingItem);
    }

    // --- DELETE
    @Transactional
    public void deleteById(Long id) {
        if (!trainingItemRepository.existsById(id)) {
            throw new NoSuchElementException("Item de Treino não encontrado com ID: " + id);
        }
        trainingItemRepository.deleteById(id);
    }
}