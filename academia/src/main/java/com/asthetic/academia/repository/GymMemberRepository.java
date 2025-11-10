package com.asthetic.academia.repository;

import com.asthetic.academia.entitys.GymMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymMemberRepository extends JpaRepository<GymMember, Long> {

    GymMember findByEnrollment(Long enrollment);
}
