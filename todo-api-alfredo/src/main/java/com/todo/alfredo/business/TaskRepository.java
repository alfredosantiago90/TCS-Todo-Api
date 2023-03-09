package com.todo.alfredo.business;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.alfredo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	public List<Task> findByStatus(String status);

	
	
}
