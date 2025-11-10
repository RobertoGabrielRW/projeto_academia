package service;

import dto.PersonalTrainerRequestDTO;
import entitys.PersonalTrainer;
import entitys.Academy;
import repository.PersonalTrainerRepository;
import repository.AcademyRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonalTrainerService {

    private final PersonalTrainerRepository trainerRepository;
    private final AcademyRepository academyRepository; // Necessário para associar o trainer à Academy

    public PersonalTrainerService(PersonalTrainerRepository trainerRepository, AcademyRepository academyRepository) {
        this.trainerRepository = trainerRepository;
        this.academyRepository = academyRepository;
    }

    // --- CREATE ---
    @Transactional
    public PersonalTrainer create(PersonalTrainerRequestDTO dto) {
        // 1. Validação de Regra de Negócio: Academia deve existir
        Academy academy = academyRepository.findById(dto.getAcademyId())
                .orElseThrow(() -> new NoSuchElementException("Academy not found for Trainer assignment."));


        PersonalTrainer trainer = new PersonalTrainer();
        trainer.setFirstName(dto.getFirstName());
        trainer.setLastName(dto.getLastName());

        trainer.setCertification(dto.getCertification());
        trainer.setHourlyRate(dto.getHourlyRate());
        trainer.setYearsOfExperience(dto.getYearsOfExperience());
        trainer.setSpecialties(dto.getSpecialties());

        // Associação
        trainer.setAcademy(academy);

        return trainerRepository.save(trainer);
    }

    // --- READ ---
    @Transactional(readOnly = true)
    public List<PersonalTrainer> findAll() {
        return trainerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PersonalTrainer> findById(Long id) {
        return trainerRepository.findById(id);
    }

    // --- UPDATE ---
    @Transactional
    public PersonalTrainer update(Long id, PersonalTrainerRequestDTO dto) {
        PersonalTrainer existingTrainer = trainerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Personal Trainer not found with ID: " + id));

        // 1. Atualiza campos comuns
        existingTrainer.setFirstName(dto.getFirstName());
        existingTrainer.setLastName(dto.getLastName());
        // ... (Outros campos de Person/Employee)

        // 2. Atualiza campos específicos
        existingTrainer.setCertification(dto.getCertification());
        existingTrainer.setHourlyRate(dto.getHourlyRate());
        existingTrainer.setYearsOfExperience(dto.getYearsOfExperience());
        existingTrainer.setSpecialties(dto.getSpecialties());

        // Se o academyId mudar, buscar a nova Academy e atualizar a associação (Opcional)

        return trainerRepository.save(existingTrainer);
    }

    // --- DELETE ---
    @Transactional
    public void deleteById(Long id) {
        if (!trainerRepository.existsById(id)) {
            throw new NoSuchElementException("Personal Trainer not found with ID: " + id);
        }
        trainerRepository.deleteById(id);
    }
}