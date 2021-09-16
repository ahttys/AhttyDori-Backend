package com.ahttys.server.user;

import static org.assertj.core.api.Assertions.*;

import com.ahttys.server.domain.Member.Member;
import com.ahttys.server.repository.MemberRepository;
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
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void clean() {
        memberRepository.deleteAll();
    }

    @Test
    public void 유저생성테스트() {
        String email = "test@test.com";
        String name = "test name";
        String password = "test1234";

        memberRepository.save(Member.builder().email(email).name(name).password(password).build());
        List<Member> members = memberRepository.findAll();

        Member member = members.get(0);
        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getName()).isEqualTo(name);
        assertThat(member.getPassword()).isEqualTo(password);
    }

    @Test
    public void 이미있는유저검색() {
        String email = "test@test.com";
        String name = "test name";
        String password = "test1234";

        memberRepository.save(Member.builder().email(email).name(name).password(password).build());
        Optional<Member> optUser = memberRepository.findByEmail(email);
        assertThat(optUser.isPresent()).isEqualTo(true);
    }
}