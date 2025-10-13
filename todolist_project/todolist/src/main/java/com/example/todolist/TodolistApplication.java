package com.example.todolist;

import com.example.todolist.domain.AppUser;
import com.example.todolist.domain.AppUserRepository;
import com.example.todolist.domain.Todo;
import com.example.todolist.domain.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

<<<<<<< HEAD
=======

>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275
@SpringBootApplication
public class TodolistApplication implements CommandLineRunner {
	private final TodoRepository todoRepository;
	private final AppUserRepository appUserRepository;

	public TodolistApplication(TodoRepository todoRepository, AppUserRepository appUserRepository) {
		this.todoRepository = todoRepository;
        this.appUserRepository = appUserRepository;
    }


    public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Todo todo = new Todo();
		todo.setContent("내용입니다.");
		appUserRepository.save(new AppUser("user", "$2a$12$9hppbNjPFuDzolufqknwS.dmzIDf9aoebLl7t9q7zZwp9q6yeDBbm", "USER"));
		todoRepository.save(todo);

	}
}
