package service;

import entitys.GymMember;
import repository.GymMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class GymMemberService {

    private final GymMemberRepository gymMemberRepository;

    public GymMemberService(GymMemberRepository gymMemberRepository) {
        this.gymMemberRepository = gymMemberRepository;
    }

    // --- CREATE
    @Transactional
    public GymMember create(GymMember member) {
        if (gymMemberRepository.findByEnrollment(member.getEnrollment()) != null) {
            throw new IllegalStateException("Matrícula já existe.");
        }
        member.setActive(true);
        return gymMemberRepository.save(member);
    }

    // --- READ
    @Transactional(readOnly = true)
    public List<GymMember> findAll() {
        return gymMemberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<GymMember> findById(Long id) {
        return gymMemberRepository.findById(id);
    }

    // --- UPDATE
    @Transactional
    public GymMember update(Long id, GymMember updatedData) {
        GymMember existingMember = gymMemberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Membro não encontrado com ID: " + id));

        existingMember.setFirstName(updatedData.getFirstName());
        existingMember.setLastName(updatedData.getLastName());
        existingMember.setEmail(updatedData.getEmail());
        existingMember.setPhone(updatedData.getPhone());
        existingMember.setAddress(updatedData.getAddress());
        existingMember.setActive(updatedData.isActive());

        return gymMemberRepository.save(existingMember);
    }

    // --- DELETE
    @Transactional
    public void deleteById(Long id) {
        if (!gymMemberRepository.existsById(id)) {
            throw new NoSuchElementException("Membro não encontrado com ID: " + id);
        }

        gymMemberRepository.deleteById(id);
    }
}