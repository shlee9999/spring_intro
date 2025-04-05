package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * 서비스는 비즈니스적 네이밍
 * 리포지토리는 개발적 네이밍
 */
@Service
public class MemberService {
/*
    // MemberServiceTest의 memberRepository와 다른 객체다. 이는 문제가 될 수 있다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();
*/

    private final MemberRepository memberRepository;

    // DI(Dependency Injection)
    // @Repository로 등록된 MemoryMemberRepository를 Spring이 넣어준다
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}
