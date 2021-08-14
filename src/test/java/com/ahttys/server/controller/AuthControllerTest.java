package com.ahttys.server.controller;

import com.ahttys.server.repository.UserRepository;
import com.ahttys.server.dto.AuthDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext ctx;

    @BeforeEach
    public void setup() {
        // 한글 깨짐 수정하기 위한 빌더 추가
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();

        // 중복검사를 위한 새로운 유저 생성
        String email = "test@test.com";
        String name = "sehwaHong";
        String password = "12341234";

        AuthDto.CreateUser user = AuthDto.CreateUser.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();

        userRepository.save(user.toEntity(passwordEncoder));
    }

    @AfterEach
    public void clean() {
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    public void joinShouldReturn200() throws Exception {
        String email = "test22@test.com";
        String name = "sehwaHong";
        String password = "12341234";

        AuthDto.CreateUser user = AuthDto.CreateUser.builder()
                                                .email(email)
                                                .name(name)
                                                .password(password)
                                                .build();

        mockMvc.perform(post("/api/auth/join")
                        .content(objectMapper.writeValueAsString(user))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    void checkEmailDuplicateShouldReturn200ByNotDuplicatedEmail() throws Exception {
        String email = "testNoDup@test.com";

        mockMvc.perform(get("/api/auth/duplicate")
                .param("email", email))
                .andExpect(status().isOk())
                .andExpect(content().json("{'message': '사용 가능한 이메일 입니다.'}"))
                .andDo(print());
    }

    @Test
    @Transactional
    void checkEmailDuplicateShouldReturn400ByDuplicatedEmail() throws Exception {
        String duplicatedEmail = "test@test.com";

        mockMvc.perform(get("/api/auth/duplicate")
                        .param("email", duplicatedEmail))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message': '사용중인 이메일 입니다.'}"))
                .andDo(print());
    }

    @Test
    @Transactional
    void checkNicknameDuplicateShouldReturn200ByNotDuplicatedName() throws Exception {
        String name = "test";

        MvcResult result = mockMvc.perform(get("/api/auth/duplicate")
                        .param("name", name))
                .andExpect(status().isOk())
                .andExpect(content().json("{'message': '사용 가능한 닉네임 입니다.'}"))
                .andDo(print())
                .andReturn();
    }

    @Test
    @Transactional
    void checkNicknameDuplicateShouldReturn400ByDuplicatedName() throws Exception {
        String duplicatedName = "sehwaHong";

        MvcResult result = mockMvc.perform(get("/api/auth/duplicate")
                        .param("name", duplicatedName))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message': '사용중인 닉네임 입니다.'}"))
                .andDo(print())
                .andReturn();
    }
}