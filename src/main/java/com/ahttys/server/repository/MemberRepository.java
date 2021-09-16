package com.ahttys.server.repository;

import com.ahttys.server.domain.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Member> findByName(String name);
}
