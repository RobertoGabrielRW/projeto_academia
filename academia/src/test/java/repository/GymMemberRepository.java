package repository;

import entitys.GymMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymMemberRepository extends JpaRepository<GymMember, Long> {

    GymMember findByEnrollment(Long enrollment);
}
