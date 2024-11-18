package com.springBoot.taskManager.service;
import com.springBoot.taskManager.Dto.NoteDTO;
import com.springBoot.taskManager.entities.NoteEntity;

import java.util.List;

public interface NotesService {
    List<NoteDTO> getNotesForTask(int taskId);
    NoteDTO addNoteForTask(int taskId,NoteDTO noteDTO);
}
