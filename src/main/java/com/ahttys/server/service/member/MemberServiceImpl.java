package com.ahttys.server.service.member;

import com.ahttys.server.domain.Member.Member;
import com.ahttys.server.dto.MemberDto;
import com.ahttys.server.repository.MemberRepository;
import com.ahttys.server.util.error.exceptions.CustomException;
import com.ahttys.server.util.mappers.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public MemberDto findMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return MemberMapper.INSTANCE.toDto(member.get());
        }
        throw new CustomException("사용자가 존재하지 않습니다.", 404);
    }
}
