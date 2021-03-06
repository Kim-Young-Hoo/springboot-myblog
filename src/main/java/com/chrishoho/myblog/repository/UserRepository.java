package com.chrishoho.myblog.repository;

import com.chrishoho.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// 자동으로 Bean 등록이 된다
@Repository // 자동 등록이 되기 때문에 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> { // User 테이블이 관리하는 Repo이면서 ID는 Integer다

    //JPA naming 전략
    // SELECT * FROM user WHERE username=? AND password=?; 가 자동으로 됨
    User findByUsernameAndPassword(String username, String password);

    @Query(value="SELECT * FROM user WHERE username=?1 AND password=?2", nativeQuery = true)
    User login(String username, String password);

}
