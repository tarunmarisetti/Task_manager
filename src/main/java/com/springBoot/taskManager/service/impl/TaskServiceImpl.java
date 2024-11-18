package com.springBoot.taskManager.service.impl;

import com.springBoot.taskManager.Mapper.TaskMapper;
import com.springBoot.taskManager.controllers.TaskController;
import com.springBoot.taskManager.entities.TaskEntity;
import com.springBoot.taskManager.Dto.TaskDTO;
import com.springBoot.taskManager.repository.TaskRepository;
import com.springBoot.taskManager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;


    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public TaskDTO addTask(TaskDTO taskDTO) throws DateTimeParseException {
        TaskEntity task=new TaskEntity();
        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        LocalDate date = LocalDate.parse(taskDTO.deadline(), DateTimeFormatter.ISO_DATE);
        task.setDeadline(date);
        task.setCompleted(false);
        TaskEntity savedTask=taskRepository.save(task);
        return TaskMapper.TaskEntityToDTO(savedTask);
    }
    @Override
    public List<TaskDTO> getAllTasks(){
        List<TaskEntity> taskEntityList=taskRepository.findAll();
        return taskEntityList.stream().map(TaskMapper::TaskEntityToDTO).toList();
    }
    @Override
    public TaskDTO getTaskById(int taskId){
        TaskEntity taskEntity=taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("no Task found with given Id"));
        return TaskMapper.TaskEntityToDTO(taskEntity);

    }

    @Override
    public TaskDTO updateTask(int taskId, TaskDTO taskDTO) {
        TaskEntity taskEntity=taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("no Task found with given Id"));
        if(taskDTO.description()!=null) {
            taskEntity.setDescription(taskDTO.description());
        }
        if(taskDTO.title()!=null) {
            taskEntity.setTitle(taskDTO.title());
        }
        if(taskDTO.deadline()!=null) {
            taskEntity.setDeadline(LocalDate.parse(taskDTO.deadline()));
        }
        if(taskDTO.completed()!=null) {
            taskEntity.setCompleted(taskDTO.completed());
        }
        TaskEntity updatedTask=taskRepository.save(taskEntity);
        return TaskMapper.TaskEntityToDTO(updatedTask);

    }

}
