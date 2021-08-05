package com.ahttys.server.controller;

import com.ahttys.server.domain.user.UserRepository;
import com.ahttys.server.dto.auth.Auth;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @AfterEach
    public void clean() {
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    public void 회원가입() throws Exception {
        String email = "test@test.com";
        String name = "sehwaHong";
        String password = "12341234";

        Auth.CreateUser user = Auth.CreateUser.builder()
                                                .email(email)
                                                .name(name)
                                                .password(password)
                                                .build();

        mockMvc.perform(post("/api/auth/join")
                        .content(objectMapper.writeValueAsString(user))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }
}