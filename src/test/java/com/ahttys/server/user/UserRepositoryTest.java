package com.ahttys.server.user;

import static org.assertj.core.api.Assertions.*;

import com.ahttys.server.domain.user.User;
import com.ahttys.server.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void clean() {
        userRepository.deleteAll();
    }

    @Test
    public void 유저생성테스트() {
        String email = "test@test.com";
        String name = "test name";
        String password = "test1234";

        userRepository.save(User.builder().email(email).name(name).password(password).build());
        List<User> users = userRepository.findAll();

        User user = users.get(0);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    public void 이미있는유저검색() {
        String email = "test@test.com";
        String name = "test name";
        String password = "test1234";

        userRepository.save(User.builder().email(email).name(name).password(password).build());
        Optional<User> optUser = userRepository.findByEmail(email);
        assertThat(optUser.isPresent()).isEqualTo(true);
    }
}