package com.ahttys.server.domain.user;

import com.ahttys.server.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<String> findByEmail(String email);
}
