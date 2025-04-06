package hello.hello_spring;

import hello.hello_spring.repository.JdbcMemberRepository;
import hello.hello_spring.repository.JdbcTemplateMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    // 현재 저장소가 정해지지 않은 상태.
    // 이후 저장소를 실제 저장소로 변경시 return new DbMemberRepository() 로만 바꿔주면 된다. 다른 코드를 전혀 건드리지 X
    // 이것이 컴포넌트 스캔 방법이 아닌 Bean 등록 방법의 장점
    @Bean
    public MemberRepository memberRepository() {
    // 기존 코드 수정 1도 없이 구현체만 바꾸어주면 애플리케이션이 돌아감. DI의 장점.
    //  return new MemoryMemberRepository();
    // return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
