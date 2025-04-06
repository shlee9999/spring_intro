package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member = " + member.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
