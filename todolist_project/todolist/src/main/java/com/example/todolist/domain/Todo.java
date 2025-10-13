package com.example.todolist.domain;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(force = true)
<<<<<<< HEAD
@AllArgsConstructor
@Builder
=======
>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275
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

<<<<<<< HEAD
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
=======
    @ManyToOne(cascade = CascadeType.ALL)
>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275
    private AppUser appUser;
}
