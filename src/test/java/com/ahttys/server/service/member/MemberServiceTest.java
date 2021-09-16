package com.ahttys.server.service.member;

import com.ahttys.server.domain.Member.Member;
import com.ahttys.server.dto.MemberDto;
import com.ahttys.server.util.mappers.MemberMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    @Test
    public void entityToDto() {
        Member member = Member.builder()
                .email("test@test.com")
                .name("test")
                .password("1234").build();

        MemberDto memberDto = MemberMapper.INSTANCE.toDto(member);
        assertEquals(member.getEmail(), memberDto.getEmail());
        assertEquals(member.getName(), memberDto.getName());
    }
}