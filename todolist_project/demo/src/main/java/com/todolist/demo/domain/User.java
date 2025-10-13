package com.todolist.demo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id; // id는 자동생성이니 생성자 생략

    @Column(nullable = false, unique = true) // 아이디 반드시 필요하고 중복 불가
    private String username;

    @Column(nullable = false) // 비밀번호는 반드시 필요하고 중복가능
    private String password;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>(); // 처음 회원 가입시 todos 목록이 없으니 생성자 생략

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
