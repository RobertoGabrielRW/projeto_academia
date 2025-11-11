package com.asthetic.academia.service;

import com.asthetic.academia.dto.PersonalTrainerRequestDTO;
import com.asthetic.academia.entitys.Academy;
import com.asthetic.academia.entitys.Address;
import com.asthetic.academia.entitys.PersonalTrainer;
import com.asthetic.academia.repository.AcademyRepository;
import com.asthetic.academia.repository.PersonalTrainerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonalTrainerService {

    private final PersonalTrainerRepository personalTrainerRepository;
    private final AcademyRepository academyRepository;

    public PersonalTrainerService(PersonalTrainerRepository personalTrainerRepository, AcademyRepository academyRepository) {
        this.personalTrainerRepository = personalTrainerRepository;
        this.academyRepository = academyRepository;
    }


    private Academy findAcademyOrThrow(Long academyId) {
        return academyRepository.findById(academyId)
                .orElseThrow(() -> new NoSuchElementException("Academia não encontrada com o ID: " + academyId));
    }

    // --- 1. CREATE (POST) ---
    @Transactional
    public PersonalTrainer create(PersonalTrainerRequestDTO trainerDTO) {
        // 1. Busca e valida a Academia
        Academy academy = findAcademyOrThrow(trainerDTO.getAcademyId());

        // 2. Mapeia o DTO para o objeto Address
        Address address = new Address();
        address.setStreet(trainerDTO.getStreet());
        address.setHouseNumber(trainerDTO.getHouseNumber());
        address.setPostalCode(trainerDTO.getPostalCode());
        address.setComplement(trainerDTO.getComplement());

        PersonalTrainer personalTrainer = new PersonalTrainer();

        personalTrainer.setFirstName(trainerDTO.getFirstName());
        personalTrainer.setLastName(trainerDTO.getLastName());
        personalTrainer.setEmail(trainerDTO.getEmail());
        personalTrainer.setPhone(trainerDTO.getPhone());
        personalTrainer.setDateOfBirth(trainerDTO.getDateOfBirth());
        personalTrainer.setGender(trainerDTO.getGender());
        personalTrainer.setAddress(address);

        // Campos específicos de PersonalTrainer
        personalTrainer.setCertification(trainerDTO.getCertification());
        personalTrainer.setYearsOfExperience(trainerDTO.getYearsOfExperience());
        personalTrainer.setHourlyRate(trainerDTO.getHourlyRate());
        personalTrainer.setSpecialties(trainerDTO.getSpecialties());


        personalTrainer.setAcademy(academy);

        return personalTrainerRepository.save(personalTrainer);
    }

    // --- 2. READ ALL (GET) ---
    public List<PersonalTrainer> findAll() {
        return personalTrainerRepository.findAll();
    }

    // --- 3. READ BY ID
    public Optional<PersonalTrainer> findById(Long id) {
        return personalTrainerRepository.findById(id);
    }

    // --- 4. UPDATE
    @Transactional
    public PersonalTrainer update(Long id, PersonalTrainerRequestDTO trainerDTO) {
        PersonalTrainer existingTrainer = personalTrainerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Personal Trainer não encontrado com o ID: " + id));


        if (!existingTrainer.getAcademy().getIdAcademia().equals(trainerDTO.getAcademyId())) {
            Academy newAcademy = findAcademyOrThrow(trainerDTO.getAcademyId());
            existingTrainer.setAcademy(newAcademy);
        }

        // Atualiza campos herdados de Person e o Address
        // Reutiliza o objeto Address existente ou cria um novo se for nulo (melhor prática!)
        Address address = existingTrainer.getAddress() != null ? existingTrainer.getAddress() : new Address();
        address.setStreet(trainerDTO.getStreet());
        address.setHouseNumber(trainerDTO.getHouseNumber());
        address.setPostalCode(trainerDTO.getPostalCode());
        address.setComplement(trainerDTO.getComplement());
        existingTrainer.setAddress(address);


        existingTrainer.setFirstName(trainerDTO.getFirstName());
        existingTrainer.setLastName(trainerDTO.getLastName());
        existingTrainer.setEmail(trainerDTO.getEmail());
        existingTrainer.setPhone(trainerDTO.getPhone());
        existingTrainer.setDateOfBirth(trainerDTO.getDateOfBirth());
        existingTrainer.setGender(trainerDTO.getGender());
        existingTrainer.setCertification(trainerDTO.getCertification());
        existingTrainer.setYearsOfExperience(trainerDTO.getYearsOfExperience());
        existingTrainer.setHourlyRate(trainerDTO.getHourlyRate());
        existingTrainer.setSpecialties(trainerDTO.getSpecialties());

        return personalTrainerRepository.save(existingTrainer);
    }

    // --- 5. DELETE
    @Transactional
    public void deleteById(Long id) {
        if (!personalTrainerRepository.existsById(id)) {
            throw new NoSuchElementException("Personal Trainer não encontrado com o ID: " + id);
        }
        personalTrainerRepository.deleteById(id);
    }

    // --- 6. CONSULTA COMPLEXA (RENOMEADO E CORRIGIDO) ---
    /**
     * Mapeia para a consulta de trainers abaixo da média de sua especialidade.
     * O parâmetro 'specialty' foi removido pois a consulta JPQL não o utiliza.
     */
    @Transactional(readOnly = true)
    public List<PersonalTrainer> findTrainersBelowAverageRate() { // RENOMEADO AQUI
        // Chama a consulta de subconsulta (abaixo da média)
        return personalTrainerRepository.findTrainersBelowAverageRateBySpecialty();
    }
}