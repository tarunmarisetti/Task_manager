package com.springBoot.taskManager.service.impl;

import com.springBoot.taskManager.Dto.NoteDTO;
import com.springBoot.taskManager.Mapper.NoteMapper;
import com.springBoot.taskManager.entities.NoteEntity;
import com.springBoot.taskManager.entities.TaskEntity;
import com.springBoot.taskManager.repository.NotesRepository;
import com.springBoot.taskManager.repository.TaskRepository;
import com.springBoot.taskManager.service.NotesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NotesService {
    private final TaskServiceImpl taskService;
    private final NotesRepository notesRepository;
    private final TaskRepository taskRepository;

    public NoteServiceImpl(TaskServiceImpl taskService, NotesRepository notesRepository, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.notesRepository = notesRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<NoteDTO> getNotesForTask(int taskId) {
        List<NoteEntity> noteList = notesRepository.findByTaskId(taskId);
        return noteList.stream()
                .map(NoteMapper::EntityToDTO)
                .toList();
    }

    @Override
    public NoteDTO addNoteForTask(int taskId, NoteDTO noteDTO) {
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));
        NoteEntity note = NoteMapper.DTOToEntity(noteDTO, taskEntity);
        NoteEntity savedNote = notesRepository.save(note);
        return NoteMapper.EntityToDTO(savedNote);
    }

}
