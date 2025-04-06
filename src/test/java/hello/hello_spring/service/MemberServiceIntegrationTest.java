package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// MemberServiceTest 는 Spring 을 이용한 테스트가 아니었다.

@SpringBootTest
/*
 테스트는 반복이 가능해야한다. DB 에는 Transaction 이라는 개념이 있어서 예를 들어 insert query 를 날리고 commit 을 하기 전에는 반영이 되지 않는다.
 @Transactional 어노테이션은 DB 데이터를 수정한 후, 테스트가 끝나면 Roll-Back 시켜준다. 즉 commit 하지 않는다.
 따라서 테스트 코드의 반복을 가능하게 해주는 매우 유용한 어노테이션이다.
 */
@Transactional
class MemberServiceIntegrationTest {

    // field AutoWired 권장하지 않지만, Test 시에는 이렇게 해도 괜찮다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    // 이제는 SpringContainer 에게 memberService, memberRepository 를 내놓으라 할 것이라 @BeforeEach 를 지운다.

    // @Transactional 어노테이션을 추가하여 @AfterEach 를 지운다.



    // 테스트는 한국어로 써도 된다.
    // 테스트는 정상 플로우도 중요하지만 예외 플로우가 훨씬 더 중요하다.
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try {
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}