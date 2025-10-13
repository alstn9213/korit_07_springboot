package com.example.todolist.domain;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
=======
>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor(force = true)
<<<<<<< HEAD
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
=======
>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275
@RequiredArgsConstructor
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String username;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column(nullable = false)
    @NonNull
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
    private List<Todo> todos;
}
