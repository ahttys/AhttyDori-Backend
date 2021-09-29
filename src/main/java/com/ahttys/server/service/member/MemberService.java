package com.ahttys.server.service.member;


import com.ahttys.server.dto.member.MemberDto;


public interface MemberService {
    MemberDto findMemberById(String token);
}
