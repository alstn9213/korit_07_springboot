package com.example.todolist.service;

<<<<<<< HEAD
import com.example.todolist.domain.AppUser;
import com.example.todolist.domain.AppUserRepository;
import com.example.todolist.domain.Todo;
import com.example.todolist.domain.TodoRepository;
import com.example.todolist.record.TodoRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
=======
import com.example.todolist.domain.AppUserRepository;
import com.example.todolist.domain.Todo;
import com.example.todolist.domain.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final AppUserRepository appUserRepository;

//  생성자
    public TodoService(TodoRepository todoRepository, AppUserRepository appUserRepository) {
        this.todoRepository = todoRepository;
        this.appUserRepository = appUserRepository;
    }

//    추가
<<<<<<< HEAD
    public Todo addTodo(Long userId, TodoRequest todoRequest) {
        Optional<AppUser> appUser = appUserRepository.findById(userId);
        AppUser currentUser = appUser.get();
        Todo todo = Todo.builder()
                .content(todoRequest.content())
                .appUser(currentUser).build();
        return todoRepository.save(todo);

=======
    public Todo addTodo(Todo todo) {
//        if(appUserRepository.findByUsername()) {
//
//        }
        return todoRepository.save(todo);
>>>>>>> fc44f2f3c3d0323182b8248119e5fcc904163275
    }

//    모든 목록 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }


//  하나 삭제
    public boolean deleteTodo(Long id) {
        if(todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    하나 수정
    @Transactional
    public Optional<Todo> updateTodo(Long id, Todo todoDetails) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setCompleted(todoDetails.isCompleted());
                    todo.setContent(todoDetails.getContent());
                    return todo;
                });
    }


//  상태 수정
//    @Transactional
//    public Optional<Todo> updateTodoStatus(Long id, Todo todoDetails) {
//        return todoRepository.findById(id)
//                .map(todo -> {
//                    todo.setContent(todoDetails.getContent());
//                    return todo;
//                });
//    }
//
////  삭제
//    public boolean clearCompletedTodos(Long id) {
//        if(todoRepository.existsById(id)) {
//            todoRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
}
