package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface 끼리는 implements가 아닌 extends
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL select m from Member m where m.name = ?
    //인터페이스 메서드 이름만으로 JPQL을 짜준다.
    //findByNameAndId(String name, Long id) 도 가능함 ㄷㄷ
    @Override
    Optional<Member> findByName(String name);
}
