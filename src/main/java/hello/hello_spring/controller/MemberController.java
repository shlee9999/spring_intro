package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 컴포넌트 스캔 방식
@Controller
public class MemberController {

    private final MemberService memberService;

    // @Service로 등록된 MemberService를 Spring이 넣어준다.
    // 생성자를 통해서 memberService가 MemberController에 주입이 된다.
    // DI의 3가지 방법 - 생성자(권장), 필드(안좋음. 바꿔치기할 방법이 없다?), setter(단점: 바꿀 일이 없는데 public으로 노출이 됨)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
