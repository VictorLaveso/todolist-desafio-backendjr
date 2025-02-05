package com.desafio.todolist.service;

import com.desafio.todolist.entity.Todo;
import com.desafio.todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> update(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list() {
        Sort.by(Sort.Direction.DESC, "prioridade");
        return todoRepository.findAll();
    }

    public List<Todo> delete(Long id) {
        todoRepository.deleteById(id);
        return list();
    }
}
