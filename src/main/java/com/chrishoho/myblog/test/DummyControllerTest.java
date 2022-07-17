package com.chrishoho.myblog.test;

import com.chrishoho.myblog.model.RoleType;
import com.chrishoho.myblog.model.User;
import com.chrishoho.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(User user) {
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        System.out.println(user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }

//    @PostMapping("/dummy/join")
//    public String join(String username, String password, String email){
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(email);
//
//        return "회원가입이 완료되었습니다.";
//    }
//

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        //User user = userRepository.findById(id).get(); // user 객체로 바로 뽑아서 줌
//        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User;
//            }
//        });
//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 유저는 없습니다 id : " + id);
//            }
//        });
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("그런 유저는 없어용");
        });
        return user; // 스프링에서는 MessageConverter라는 애가 응답시에 자동으로 자바오브젝트를 Jackson 라이브러리를 통해 Json화함
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

//    @GetMapping("/dummy/user")
//    public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//            Page<User> users = userRepository.findAll(pageable);
//            return users;
//    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pageUsers = userRepository.findAll(pageable);
        List<User> users = pageUsers.getContent();
        return users;
    }


    @Transactional // 더티체킹. DB에서 가지고 온 user 오브젝트가 영속화 되었을 때 거기에 변경점이 발생하면 자동 commit이 이루어지게 됨.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // RequestBody로 json형태로 받음
        User user = userRepository.findById(id).orElseThrow(
                () -> {
                    return new IllegalArgumentException("업데이트 실패");
                }
        );
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제 실패 id : " + id;
        }
        return "삭제 완료 id : " + id ;
    }
}
