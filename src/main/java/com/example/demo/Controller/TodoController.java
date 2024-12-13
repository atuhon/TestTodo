package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Service.TodoService;

@Controller
public class TodoController {
private final TodoService todoservice;

public TodoController(TodoService todoservice) {
	super();
	this.todoservice = todoservice;
}

@GetMapping("/todo")
public String selectTodo(Model model) {
	var todo=todoservice.SelectTodo();
	var find=todoservice.findOne(0);
	System.out.println(find);
	model.addAttribute("select", todo);
	return "index";
	
}


}
