package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Common.Todo;
import com.example.demo.Repository.TodoMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
public class TodoService {
	private final TodoMapper todomap;

	public TodoService(TodoMapper todomap) {
		super();
		this.todomap = todomap;
	}
	public List<Todo> SelectTodo(){

		return todomap.findAll();

	}
	public Todo findOne(int id) {
		System.out.println(todomap.findOne(0));
		return todomap.findOne(id);
	}
	

}
