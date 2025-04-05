package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        // helloController -> viewResolver - hello-template.html을 찾고 렌더링을 위해 thymeleaf로 넘긴다. -
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        // html 태그 없이 그냥 날 것의 String을 내려줌
        // helloController -> @ResponseBody -> HttpMessageConverter -> StringConverter (StringHttpMessageConverter)
        return "hello " + name; // 이 문자열을 내려줌.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // 반환 타입이 객체면 기본적으로 JSON 형태로 반환한다.
        // helloController -> @ResponseBody -> HttpMessageConverter -> JsonConverter (MappingJackson2HttpMessageConverter)
        return hello;

    }


    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
