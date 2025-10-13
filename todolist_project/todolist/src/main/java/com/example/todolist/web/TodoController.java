package com.example.todolist.web;

import com.example.todolist.domain.AppUserRepository;
import com.example.todolist.domain.Todo;
<<<<<<< HEAD
import com.example.todolist.record.TodoRequest;
import com.example.todolist.service.TodoService;
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
import com.example.todolist.service.TodoService;
>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
// 조회
<<<<<<< HEAD
    @GetMapping("/todos/{id}")
=======
    @GetMapping("/todos")
>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }
//    추가
<<<<<<< HEAD
    @PostMapping("/todos/{id}")
    public ResponseEntity<Todo> addTodo(@PathVariable Long id, @RequestBody TodoRequest content) {
        Todo savedTodo = todoService.addTodo(id, content);
=======
    @PostMapping("/todos")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoService.addTodo(todo);
>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
//   하나 수정
    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        return todoService.updateTodo(id, todoDetails)
                .map(updateTodo ->
                        ResponseEntity.ok().body(updateTodo))
                .orElse(ResponseEntity.notFound().build());
    }
//  하나 삭제
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if(todoService.deleteTodo(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
