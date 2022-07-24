package com.chrishoho.myblog.controller.api;

import com.chrishoho.myblog.dto.ResponseDto;
import com.chrishoho.myblog.model.RoleType;
import com.chrishoho.myblog.model.User;
import com.chrishoho.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;


//    HttpSession을 이렇게 받을 수도 있음
//    @Autowired
//    private HttpSession session;


    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("로그인 요청됨");
        User principal = userService.로그인(user);
        System.out.println(principal);

        if (principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
