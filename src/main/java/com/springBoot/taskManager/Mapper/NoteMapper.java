package com.springBoot.taskManager.Mapper;

import com.springBoot.taskManager.Dto.NoteDTO;
import com.springBoot.taskManager.entities.NoteEntity;
import com.springBoot.taskManager.entities.TaskEntity;

public class NoteMapper {
    public static NoteDTO EntityToDTO(NoteEntity noteEntity){
        return new NoteDTO(noteEntity.getId(),noteEntity.getTitle(),noteEntity.getBody(),noteEntity.getTask().getId());
    }
    public static NoteEntity DTOToEntity(NoteDTO noteDTO, TaskEntity task){
        return new NoteEntity(noteDTO.id(),noteDTO.title(),noteDTO.body(),task);

    }
}
