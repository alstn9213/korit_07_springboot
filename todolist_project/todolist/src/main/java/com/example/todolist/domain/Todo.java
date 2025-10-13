package com.example.todolist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;
}
