package com.todo.alfredo.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.alfredo.model.Task;

@Service
public class TaskService {
	private final TaskRepository taskRepository;

	@Autowired
	public TaskService(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}
	
	//Get All
	public List<Task> getAll(){
		return taskRepository.findAll();
	}
	
	//Get by id
	
	public Optional<Task> getById(Long id) {
		return taskRepository.findById(id);
	}
	
	//Create
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
	//filter by status
	public List<Task> findByStatus(String status){
		return taskRepository.findByStatus(status);
	}
	
	//Delete
	public boolean deleteTask(Long id) {
		try {
			taskRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	
	//patch
	public boolean partialUpdate(long id, String key, String value) {
		Optional<Task> opt = taskRepository.findById(id);
		
		if(opt.isPresent()) {
			Task task = opt.get();
			
			if(key.equalsIgnoreCase("completed")) {
				task.setCompleted(value);
			}
			
			taskRepository.save(task);
			return true;
		}else {
			return false;
		}
	}
	
	
	
}

