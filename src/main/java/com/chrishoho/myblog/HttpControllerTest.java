package com.chrishoho.myblog;


import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest : ";


    @GetMapping("/http/lombok")
    public String lombokTest() {
//        Member m = new Member(1, "chris", "1234", "chrishoho@naver.com");
        Member m = Member.builder().username("chris").password("1234").email("chrishoho@naver.com").build();
        System.out.println(TAG + "getter : " + m.getId());
        m.setId(5000);
        System.out.println(TAG + "after setter : " + m.getId());
        return "lombok test 완료";
    }



//    @GetMapping("/http/get")
//    public String getTest(@RequestParam int id, @RequestParam String username) {
//        return "get request" + id + username;
//    }

    @GetMapping("/http/get")
    public String getTest(Member m) {
        return m.getId() + " " + m.getUsername() + " " + m.getPassword() + " " + m.getEmail();
    }

//    @PostMapping("http/post")
//    public String postTest(Member m) {
//        return m.getId() + " " + m.getUsername() + " " + m.getPassword() + " " + m.getEmail();
//    }

//    @PostMapping("http/post")
//    public String postTest(@RequestBody String text) {
//        return text;
//    }

    @PostMapping("http/post")
    public String postTest(@RequestBody Member m) {
        return m.getId() + " " + m.getUsername() + " " + m.getPassword() + " " + m.getEmail();
    }

    @PutMapping("http/put")
    public String putTest(@RequestBody Member m) {
        return m.getId() + " " + m.getUsername() + " " + m.getPassword() + " " + m.getEmail();
    }

    @DeleteMapping("http/delete")
    public String deleteTest(@RequestBody Member m) {
        return m.getId() + " " + m.getUsername() + " " + m.getPassword() + " " + m.getEmail();
    }

}
