package com.springBoot.taskManager.repository;

import com.springBoot.taskManager.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {
}
