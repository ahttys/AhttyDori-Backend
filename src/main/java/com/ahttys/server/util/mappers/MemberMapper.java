package com.ahttys.server.util.mappers;

import com.ahttys.server.domain.Member.Member;
import com.ahttys.server.dto.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);
    MemberDto toDto(Member member);
}
