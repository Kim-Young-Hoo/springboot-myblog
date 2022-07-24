package com.chrishoho.myblog.service;

import com.chrishoho.myblog.model.User;
import com.chrishoho.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true) // 정합성을 유지시켜줌
    public User 로그인(User user) {
        System.out.println("로그인 서비스");
        System.out.println(user);
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

}
