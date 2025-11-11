package com.asthetic.academia.service;

import com.asthetic.academia.dto.GymMemberRequestDTO;
import com.asthetic.academia.entitys.GymMember;
import com.asthetic.academia.entitys.Academy;
import com.asthetic.academia.entitys.PersonalTrainer;
import com.asthetic.academia.entitys.Address; // IMPORT NECESSÁRIO
import com.asthetic.academia.repository.GymMemberRepository;
import com.asthetic.academia.repository.AcademyRepository;
import com.asthetic.academia.repository.PersonalTrainerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GymMemberService {

    private final GymMemberRepository memberRepository;
    private final AcademyRepository academyRepository;
    private final PersonalTrainerRepository trainerRepository;

    public GymMemberService(
            GymMemberRepository memberRepository,
            AcademyRepository academyRepository,
            PersonalTrainerRepository trainerRepository) {

        this.memberRepository = memberRepository;
        this.academyRepository = academyRepository;
        this.trainerRepository = trainerRepository;
    }

    // --- CREATE (Método Corrigido) ---
    @Transactional
    public GymMember create(GymMemberRequestDTO dto) {
        // Validações de Relacionamento (Regra de Negócio)
        Academy academy = academyRepository.findById(dto.getAcademyId())
                .orElseThrow(() -> new NoSuchElementException("Academy not found."));

        PersonalTrainer trainer = trainerRepository.findById(dto.getPersonalTrainerId())
                .orElseThrow(() -> new NoSuchElementException("Personal Trainer not found."));

        // Mapeamento DTO -> Entidade
        GymMember member = new GymMember();

        // Mapeamento dos campos herdados de Person (incluindo contato)
        member.setFirstName(dto.getFirstName());
        member.setLastName(dto.getLastName());
        member.setDateOfBirth(dto.getDateOfBirth());
        member.setGender(dto.getGender());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());

        // CRÍTICO: Mapeamento do Endereço (Corrigindo o 500)
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setHouseNumber(dto.getHouseNumber());
        address.setPostalCode(dto.getPostalCode());
        address.setComplement(dto.getComplement());

        member.setAddress(address); // Atribui o objeto Address à entidade Person/GymMember

        // Mapeamento do campo específico
        member.setEnrollment(dto.getEnrollment());

        // Associações
        member.setAcademy(academy);
        member.setPersonalTrainer(trainer);

        return memberRepository.saveAndFlush(member);
    }

    // --- READ ---
    @Transactional(readOnly = true)
    public List<GymMember> findAll() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<GymMember> findById(Long id) {
        return memberRepository.findById(id);
    }

    // --- UPDATE ---
    @Transactional
    public GymMember update(Long id, GymMemberRequestDTO dto) {
        GymMember existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Gym Member not found with ID: " + id));

        // Atualiza campos de Person
        existingMember.setFirstName(dto.getFirstName());
        existingMember.setLastName(dto.getLastName());
        existingMember.setDateOfBirth(dto.getDateOfBirth());
        existingMember.setGender(dto.getGender());
        existingMember.setEnrollment(dto.getEnrollment());
        existingMember.setEmail(dto.getEmail()); // NOVO
        existingMember.setPhone(dto.getPhone()); // NOVO

        // Atualiza Endereço
        Address updatedAddress = existingMember.getAddress() != null ? existingMember.getAddress() : new Address();
        updatedAddress.setStreet(dto.getStreet());
        updatedAddress.setHouseNumber(dto.getHouseNumber());
        updatedAddress.setPostalCode(dto.getPostalCode());
        updatedAddress.setComplement(dto.getComplement());
        existingMember.setAddress(updatedAddress);

        return memberRepository.save(existingMember);
    }

    // --- DELETE ---
    @Transactional
    public void deleteById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new NoSuchElementException("Gym Member not found with ID: " + id);
        }
        memberRepository.deleteById(id);
    }
}