package controller;

import dto.GymMemberRequestDTO;
import entitys.GymMember;
import service.GymMemberService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/members")
public class GymMemberController {

    private final GymMemberService memberService;

    public GymMemberController(GymMemberService memberService) {
        this.memberService = memberService;
    }

    // --- 1. CREATE (POST) ---
    @PostMapping
    public ResponseEntity<GymMember> createMember(@Valid @RequestBody GymMemberRequestDTO memberDTO) {
        try {
            GymMember createdMember = memberService.create(memberDTO);
            return new ResponseEntity<>(createdMember, HttpStatus.CREATED); // 201 Created
        } catch (NoSuchElementException e) {
            // Exceção de negócio (Academy ou Trainer não encontrado)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // --- 2. READ ALL (GET) ---
    @GetMapping
    public ResponseEntity<List<GymMember>> getAllMembers() {
        List<GymMember> members = memberService.findAll();
        return ResponseEntity.ok(members);
    }

    // --- 3. READ BY ID (GET) ---
    @GetMapping("/{id}")
    public ResponseEntity<GymMember> getMemberById(@PathVariable Long id) {
        return memberService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
    }

    // --- 4. UPDATE (PUT) ---
    @PutMapping("/{id}")
    public ResponseEntity<GymMember> updateMember(@PathVariable Long id, @Valid @RequestBody GymMemberRequestDTO memberDTO) {
        try {
            GymMember updatedMember = memberService.update(id, memberDTO);
            return ResponseEntity.ok(updatedMember);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // --- 5. DELETE (DELETE) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        try {
            memberService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}