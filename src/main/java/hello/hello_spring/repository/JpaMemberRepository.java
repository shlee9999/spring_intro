package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class JpaMemberRepository implements MemberRepository {
/*
    JPA를 사용하려면 EntityManager을 주입받아야 한다. spring이 jpa를 사용하면 자동으로 만들어서 주입해준다.
    (build.gradle -> implementation 'org.springframework.boot:spring-boot-starter-data-jpa')
 */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
        // 이게 끝임 ㅋㅋㅋㅋㅋㅋ
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        /*
             JPQL 이라는 쿼리 언어. 일반 SQL 과 다르다.
             테이블이 아닌 객체를 대상으로 쿼리를 날리는 것
             아래 m은 객체 자체를 선택했다고 보면 됨.
             기존 SQL이었다면 id, name 찾고 다시 매핑하고 ...
         */

        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
