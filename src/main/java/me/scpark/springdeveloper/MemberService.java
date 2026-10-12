package me.scpark.springdeveloper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 저장 (Create)
    @Transactional
    public MemberResponse saveMember(MemberCreateRequest request) {
        Member member = request.toEntity();
        Member savedMember = memberRepository.save(member);
        return new MemberResponse(savedMember);
    }

    // 전체 회원 조회 (Read)
    public List<MemberResponse> findAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::new)
                .toList();
    }
}