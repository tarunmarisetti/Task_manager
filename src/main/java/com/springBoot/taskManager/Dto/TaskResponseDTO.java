package com.springBoot.taskManager.Dto;

import com.springBoot.taskManager.entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private int id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean completed;
    private List<NoteDTO> notes;
}
