package service;

import abstracts.Training;
import repository.TrainingRepository;
import repository.GymMemberRepository;
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

    // --- CREATE
    @Transactional
    public Training create(Training training) {

        gymMemberRepository.findById(training.getGymMember().getId())
                .orElseThrow(() -> new NoSuchElementException("Membro não encontrado para criar o Treino."));

        return trainingRepository.save(training);
    }

    // --- READ
    @Transactional(readOnly = true)
    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Training> findById(Long id) {
        return trainingRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Training> findTrainingsByMember(Long memberId) {
        return trainingRepository.findByGymMemberId(memberId);
    }

    // --- UPDATE
    @Transactional
    public Training update(Long id, Training updatedData) {

        Training existingTraining = trainingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Treino não encontrado com ID: " + id));

        existingTraining.setName(updatedData.getName());
        existingTraining.setIntensity(updatedData.getIntensity());
        existingTraining.setGoal(updatedData.getGoal());

        return trainingRepository.save(existingTraining);
    }

    // --- DELETE
    @Transactional
    public void deleteById(Long id) {
        if (!trainingRepository.existsById(id)) {
            throw new NoSuchElementException("Treino não encontrado com ID: " + id);
        }

        trainingRepository.deleteById(id);
    }
}