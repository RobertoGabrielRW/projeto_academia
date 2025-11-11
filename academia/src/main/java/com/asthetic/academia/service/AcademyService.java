package com.asthetic.academia.service;

import com.asthetic.academia.dto.AcademyRequestDTO;
import com.asthetic.academia.entitys.Academy;
import com.asthetic.academia.entitys.Address;
import com.asthetic.academia.repository.AcademyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AcademyService {

    private final AcademyRepository academyRepository;

    public AcademyService(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    // --- 1. CREATE (POST) ---
    @Transactional
    public Academy create(AcademyRequestDTO academyDTO) {
        // Mapeia o DTO para o objeto Address @Embedded
        Address address = new Address();
        address.setStreet(academyDTO.getStreet());
        address.setHouseNumber(academyDTO.getHouseNumber());
        address.setPostalCode(academyDTO.getPostalCode());
        address.setComplement(academyDTO.getComplement());

        // Mapeia o DTO para a Entidade Academy
        Academy academy = new Academy();
        academy.setCorporateTaxId(academyDTO.getCorporateTaxId());
        academy.setEmail(academyDTO.getEmail());
        academy.setPhone(academyDTO.getPhone());
        academy.setAddress(address);

        return academyRepository.save(academy);
    }

    // --- 2. READ ALL (GET) ---
    public List<Academy> findAll() {
        return academyRepository.findAll();
    }

    // --- 3. READ BY ID (GET) ---
    public Optional<Academy> findById(Long id) {
        return academyRepository.findById(id);
    }

    // --- 4. UPDATE (PUT) ---
    @Transactional
    public Academy update(Long id, AcademyRequestDTO academyDTO) {
        Academy existingAcademy = academyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Academia não encontrada com o ID: " + id));

        // Atualiza campos básicos
        existingAcademy.setCorporateTaxId(academyDTO.getCorporateTaxId());
        existingAcademy.setEmail(academyDTO.getEmail());
        existingAcademy.setPhone(academyDTO.getPhone());

        // Atualiza o endereço @Embedded
        Address existingAddress = existingAcademy.getAddress() != null ? existingAcademy.getAddress() : new Address();
        existingAddress.setStreet(academyDTO.getStreet());
        existingAddress.setHouseNumber(academyDTO.getHouseNumber());
        existingAddress.setPostalCode(academyDTO.getPostalCode());
        existingAddress.setComplement(academyDTO.getComplement());

        existingAcademy.setAddress(existingAddress);

        return academyRepository.save(existingAcademy);
    }

    // --- 5. DELETE (DELETE) ---
    @Transactional
    public void deleteById(Long id) {
        if (!academyRepository.existsById(id)) {
            throw new NoSuchElementException("Academia não encontrada com o ID: " + id);
        }
        academyRepository.deleteById(id);
    }
}