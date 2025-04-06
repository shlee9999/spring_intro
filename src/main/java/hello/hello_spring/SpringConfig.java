package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    // 현재 저장소가 정해지지 않은 상태.
    // 이후 저장소를 실제 저장소로 변경시 return new DbMemberRepository() 로만 바꿔주면 된다. 다른 코드를 전혀 건드리지 X
    // 이것이 컴포넌트 스캔 방법이 아닌 Bean 등록 방법의 장점
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
