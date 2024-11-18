package com.springBoot.taskManager.service;

import com.springBoot.taskManager.Dto.TaskDTO;
import com.springBoot.taskManager.entities.TaskEntity;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public interface TaskService {
    TaskDTO addTask(TaskDTO taskDTO) throws DateTimeParseException;
    List<TaskDTO> getAllTasks();
    TaskDTO getTaskById(int taskId);

    TaskDTO updateTask(int taskId,TaskDTO taskDTO);

}
