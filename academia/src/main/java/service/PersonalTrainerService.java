package service;

import entitys.PersonalTrainer;
import repository.PersonalTrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class PersonalTrainerService {

    private final PersonalTrainerRepository personalTrainerRepository;

    public PersonalTrainerService(PersonalTrainerRepository personalTrainerRepository) {
        this.personalTrainerRepository = personalTrainerRepository;
    }

    // --- CREATE
    @Transactional
    public PersonalTrainer create(PersonalTrainer trainer) {
        trainer.setAvailable(true); // Regra de negócio
        return personalTrainerRepository.save(trainer);
    }

    // --- READ
    @Transactional(readOnly = true)
    public List<PersonalTrainer> findAll() {
        return personalTrainerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PersonalTrainer> findById(Long id) {
        return personalTrainerRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<PersonalTrainer> findAvailableBySpecialty(String specialty) {
        return personalTrainerRepository.findBySpecialtiesContainingAndAvailableTrue(specialty);
    }

    // --- UPDATE
    @Transactional
    public PersonalTrainer update(Long id, PersonalTrainer updatedData) {
        PersonalTrainer existingTrainer = personalTrainerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Treinador não encontrado com ID: " + id));

        existingTrainer.setCertification(updatedData.getCertification());
        existingTrainer.setHourlyRate(updatedData.getHourlyRate());
        existingTrainer.setAvailable(updatedData.getAvailable());
        existingTrainer.setScheduleNotes(updatedData.getScheduleNotes());


        existingTrainer.setFirstName(updatedData.getFirstName());
        existingTrainer.setEmail(updatedData.getEmail());

        return personalTrainerRepository.save(existingTrainer);
    }

    // --- DELETE
    @Transactional
    public void deleteById(Long id) {
        if (!personalTrainerRepository.existsById(id)) {
            throw new NoSuchElementException("Treinador não encontrado com ID: " + id);
        }
        personalTrainerRepository.deleteById(id);
    }
}