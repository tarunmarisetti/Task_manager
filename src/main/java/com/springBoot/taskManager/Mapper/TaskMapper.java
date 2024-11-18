package com.springBoot.taskManager.Mapper;

import com.springBoot.taskManager.entities.TaskEntity;
import com.springBoot.taskManager.Dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskMapper {
    public static TaskDTO TaskEntityToDTO(TaskEntity taskEntity){
        TaskDTO taskDTO=new TaskDTO(taskEntity.getId(),taskEntity.getTitle(),taskEntity.getDescription(),taskEntity.getDeadline().toString(),taskEntity.isCompleted());

        return taskDTO;
    }
    public static TaskEntity DTOToEntity(TaskDTO taskDTO){
        TaskEntity taskEntity=new TaskEntity(taskDTO.id(),taskDTO.title(),taskDTO.description(), LocalDate.parse(taskDTO.deadline()), taskDTO.completed());

        return taskEntity;
    }

}
