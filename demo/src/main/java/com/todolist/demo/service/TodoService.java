package com.todolist.demo.service;

import com.todolist.demo.domain.Todo;
import com.todolist.demo.domain.TodoRepository;
import com.todolist.demo.domain.User;
import com.todolist.demo.domain.UserRepository;
import com.todolist.demo.dto.TodoRequestDto;
import com.todolist.demo.dto.TodoRequestRecord;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public TodoService(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User를 찾을 수 없습니다."));
    }

//    To-do 객체 내에 있는 User와, 지금 요청을 보내는 사용자가 동일한지 확인하기 위해 본인 확인 로직 추가.
    private void checkOwnership(Todo todo) throws AccessDeniedException {
        if(!todo.getUser().equals(getCurrentUser())) {
            throw new AccessDeniedException("해당 todo에 접근할 수 없습니다.");
        }
    }

    // 1. 사용자 구분 없이 모든 할 일 목록 조회
    @Transactional
    public List<Todo> getTodos() {

        return todoRepository.findAll();
    }

//     2. 현재 사용자의 모든 할 일 목록 조회
    @Transactional(readOnly = true)
    public List<Todo> getTodosForCurrentUser() {
        User currentUser = getCurrentUser();
        return todoRepository.findByUserId(currentUser.getId());
    }

//    3. 새로운 to-do 추가
//    DTO version
    public Todo createTodo(TodoRequestDto todoRequestDto) {
        User currentUser = getCurrentUser();
        Todo newTodo = new Todo(currentUser, todoRequestDto.getContent());
        return todoRepository.save(newTodo);
    }

//    Record version
//    레코드를 쓴 메서드와 안쓴 메서드의 기능은 완전히 동일하다.
    public Todo createTodo2(TodoRequestRecord todoRequestRecord) {
        User currentUser = getCurrentUser();
        Todo newTodo = new Todo(currentUser, todoRequestRecord.content());
        return todoRepository.save(newTodo);
    }

//   4. 할 일 내용 수정
    @Transactional
    public Todo updateTodoContent(Long id, TodoRequestDto updateDto) throws AccessDeniedException { // 본인의 것이 아닌 것을 수정하려할 때 예외 발생
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 todo가 없습니다. : " + id));
        checkOwnership(todo);
        todo.setContent(updateDto.getContent());
        return todoRepository.save(todo);
    }

//    5. 할 일 삭제
    @Transactional
    public void deleteTodo(Long id) throws AccessDeniedException {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 todo가 없습니다. : " + id));
        checkOwnership(todo);
        todoRepository.delete(todo);
    }

//    6. 할 일 완료 상태 토글
    @Transactional
    public Todo toggleTodoStatus(Long id) throws AccessDeniedException {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 todo가 없습니다. : " + id));
        checkOwnership(todo);
        todo.setCompleted(!todo.isCompleted());
        return todoRepository.save(todo);
    }

//    7. 완료된 할 일 전체 삭제하는 로직
    @Transactional
    public void clearCompletedTodos() throws AccessDeniedException {
        User currentUser = getCurrentUser();
        todoRepository.deleteByUserAndIsCompleted(currentUser, true);
    }
}
