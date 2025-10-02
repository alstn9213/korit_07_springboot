package com.example.todolist.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(force = true)
@Getter
@Setter
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    private boolean isCompleted;

    @ManyToOne(cascade = CascadeType.ALL)
    private AppUser appUser;
}
