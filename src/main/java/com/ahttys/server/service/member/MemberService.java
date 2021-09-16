package com.ahttys.server.service.member;

import com.ahttys.server.dto.MemberDto;

public interface MemberService {
    MemberDto findMemberById(Long id);
}
