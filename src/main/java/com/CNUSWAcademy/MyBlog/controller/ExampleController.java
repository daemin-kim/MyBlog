package com.CNUSWAcademy.MyBlog.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {
    
    // Model 객체는 따로 생성할 필요 없이 코드처럼 인자로 선언하기만 하면 스프링이 알아서 만들어줌.
    // addAttribute() 메서드로 모델에 값을 저장함.
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("김대민");
        examplePerson.setAge(23);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());
        
        // 클래스에 붙은 @Controller 애너테이션을 보고 뷰의 이름을 반환하는 것
        // 스프링 부트는 컨트롤러의 @Controller 애너테이션을 보고 "반환하는 값의 이름을 가진 뷰의 파일을 찾아라" 라고 이해함.
        // 그 다음 resource/templates 디렉토리에서 example.html을 찾은 다음 웹 브라우저에서 해당 파일을 보여줌.
        return "example";
    }

    @Getter
    @Setter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
