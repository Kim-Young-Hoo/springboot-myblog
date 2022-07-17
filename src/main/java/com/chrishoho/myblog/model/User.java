package com.chrishoho.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data // Getter, Setter를 만들어줌
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User Class가 mysql에 테이블 생성이 된다
// @DynamicInsert // null인 칼럼은 제외해서 insert 시켜주도록
public class User {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY: 사용 중인 db의 넘버링 전략을 따른다
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'")
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp // 시간 자동 입력
    private Timestamp createDate;
}

