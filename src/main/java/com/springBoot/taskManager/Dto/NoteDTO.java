package com.springBoot.taskManager.Dto;


import com.springBoot.taskManager.entities.TaskEntity;

public record NoteDTO(int id, String title, String body, int taskId){}
