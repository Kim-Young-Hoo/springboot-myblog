package com.chrishoho.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User Class가 mysql에 테이블 생성이 된다
public class User {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY: 사용 중인 db의 넘버링 전략을 따른다
    private int id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role;

    @CreationTimestamp // 시간 자동 입력
    private Timestamp createDate;
}

