package com.spring.toyproject.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/*
    사용자 엔터티
    - 인증 시스템의 핵심 엔터티로 기본 사용자 정보 관리
 */
@Entity
@Table(name = "tbl_user")
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "user_email", unique = true, nullable = false, length = 100)
    private String email;

    // 패스워드는 사용자가 지정한 길이가 8~20 글자여도 결국에 데이터베이스에는 암호화로 되어 해시로 들어간다.
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    // 모든 테이블에는 생성시간과 마지막 수정시간이 필수
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /*    // 사용자의 아이디나 패스워드 및 다른 것들을 조정하면 안 되기 때문에 @Builder를 쓰면 안 된다.
    void test() {
        User.builder()
                .id(3L)
                .password("123123123")
                .build();
    }*/

    // AllArgsConstructor 대신에 따로 생성자를 하나 만듦
    @Builder
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
