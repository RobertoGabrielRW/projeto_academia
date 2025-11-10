package com.asthetic.academia.service;

import com.asthetic.academia.entitys.Academy;
import com.asthetic.academia.repository.AcademyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class AcademyService {

    private final AcademyRepository academyRepository;

    public AcademyService(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    // --- CREATE
    @Transactional
    public Academy create(Academy academy) {
        if (academyRepository.findByCorporateTaxId(academy.getCorporateTaxId()) != null) {
            throw new IllegalStateException("CNPJ já cadastrado.");
        }
        return academyRepository.save(academy);
    }

    // --- READ
    @Transactional(readOnly = true)
    public List<Academy> findAll() {
        return academyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Academy> findById(Long id) {
        return academyRepository.findById(id);
    }

    // --- UPDATE
    @Transactional
    public Academy update(Long id, Academy updatedData) {
        Academy existingAcademy = academyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Academia não encontrada com ID: " + id));


        existingAcademy.setEmail(updatedData.getEmail());
        existingAcademy.setPhone(updatedData.getPhone());



        return academyRepository.save(existingAcademy);
    }

    // DELETE
    @Transactional
    public void deleteById(Long id) {
        if (!academyRepository.existsById(id)) {
            throw new NoSuchElementException("Academia não encontrada com ID: " + id);
        }

        academyRepository.deleteById(id);
    }
}