package com.example.springkadaitodo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.repository.ToDoRepository;

@Service
public class ToDoService {
	// ToDoRepositoryの依存注入
	private final ToDoRepository toDoRepository;
	
	public ToDoService(ToDoRepository toDoRepository) {
		this.toDoRepository = toDoRepository;
	}
	
	// ToDoリストの一括取得
	public List<ToDo> getAllTodos(){
		return toDoRepository.findAll();
	}
}