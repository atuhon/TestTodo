package com.example.demo.Common;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class Todo {
	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private Integer id;
	private String name;
	private String description;
	

}
