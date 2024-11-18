package com.springBoot.taskManager.controllers;
import com.springBoot.taskManager.Dto.CreateNoteResponseDTO;
import com.springBoot.taskManager.Dto.NoteDTO;
import com.springBoot.taskManager.entities.NoteEntity;
import com.springBoot.taskManager.service.impl.NoteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    private NoteServiceImpl noteService;

    public NotesController(NoteServiceImpl noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getNotes(@PathVariable("taskId") int taskId){
        var notes=noteService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }
    @PostMapping
    public ResponseEntity<CreateNoteResponseDTO> addNotes(@PathVariable("taskId") int taskId, @RequestBody NoteDTO noteDTO){
        NoteDTO note=noteService.addNoteForTask(taskId,noteDTO);
        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId,note));
    }

}
