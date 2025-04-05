package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 컴포넌트 스캔 방식
@Controller
public class MemberController {

    private final MemberService memberService;

    // @Service로 등록된 MemberService를 Spring이 넣어준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
