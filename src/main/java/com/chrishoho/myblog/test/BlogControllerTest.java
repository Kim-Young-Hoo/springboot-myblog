package com.chrishoho.myblog.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 어노테이션이 붙어있는 애들을 스프링 컨테이너에 넣어서 관리해준다 (IoC)
public class BlogControllerTest {

    @GetMapping("/test/hello")
    public String hello(){
        return "<h1>hello spring boot</h1>";
    }

}
