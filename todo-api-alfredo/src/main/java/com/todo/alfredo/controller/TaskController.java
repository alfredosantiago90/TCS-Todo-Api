package com.todo.alfredo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.alfredo.business.TaskService;
import com.todo.alfredo.data.PatchDto;
import com.todo.alfredo.model.Task;

@RestController
@RequestMapping("/api")
public class TaskController {
	
	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}
	
	
	
	//methods
	
	@GetMapping("/tasks")
	public List<Task> getAll(){
		return taskService.getAll();
	}
	
	@GetMapping(path = "/tasks/{id}")
	public Optional<Task> getById(@PathVariable("id") Long id) {
		return taskService.getById(id);
	}
	
	@PostMapping("/tasks")
	public Task addTask(@RequestBody Task task) {
		return taskService.createTask(task);
	}
	
	//filter
	@GetMapping(path="/status") //http://localhost:8080/api/status?type=on_time || late
	public List<Task> findByStatus(@RequestParam("type") String status){
		return taskService.findByStatus(status);
	}
	
	//update
	@PutMapping("/tasks/update")
	public Task updateTask(@RequestBody Task task) {
		return taskService.createTask(task);
	}
	
	//Delete
	@DeleteMapping(path= "/tasks/{id}")
	public boolean deleteTask(@PathVariable("id") Long id) {
		return taskService.deleteTask(id);
	}
	
	//patch
	@PatchMapping("/tasks/{id}")
	public ResponseEntity<Boolean> updatePartially(@PathVariable(name="id") long id, @RequestBody PatchDto dto){
		if(dto.getOp().equalsIgnoreCase("update")) {
			boolean result = taskService.partialUpdate(id, dto.getKey(), dto.getValue());
			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}
}
