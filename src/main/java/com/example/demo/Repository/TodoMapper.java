package com.example.demo.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.Common.Todo;

@Mapper
public interface TodoMapper {

	List<Todo>findAll();
	Todo insertTodo(String name,String descripton);
	
	
	


}
