package hello.hello_spring.domain;

/*
    JPA는 ORM 기술 (객체와 관계형 DB를 매핑한다)
 */

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY는 DB가 알아서 생성해 주는 것
    private Long id;

    /*
        @Column(name = "username") <- DB의 column 명이 username인 경우
     */
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
