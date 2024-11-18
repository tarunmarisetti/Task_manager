package com.springBoot.taskManager.controllers;

import com.springBoot.taskManager.Dto.NoteDTO;
import com.springBoot.taskManager.Dto.TaskResponseDTO;
import com.springBoot.taskManager.Mapper.TaskMapper;
import com.springBoot.taskManager.entities.NoteEntity;
import com.springBoot.taskManager.service.impl.NoteServiceImpl;
import com.springBoot.taskManager.service.impl.TaskServiceImpl;
import com.springBoot.taskManager.Dto.ErrorHandlingDTO;
import com.springBoot.taskManager.Dto.TaskDTO;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskServiceImpl taskService;
    private final NoteServiceImpl noteService;
    private ModelMapper modelMapper=new ModelMapper();

    public TaskController(TaskServiceImpl taskService, NoteServiceImpl noteService) {
        this.taskService = taskService;
        this.noteService = noteService;
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<List<TaskDTO>> getTasks(){
        List<TaskDTO> taskDTOList=new ArrayList<>();

        List<TaskDTO> taskEntities= taskService.getAllTasks();
        return ResponseEntity.ok(taskEntities);

    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable int id){
        TaskDTO task=taskService.getTaskById(id);
        System.out.println(task);
        List<NoteDTO> notes=noteService.getNotesForTask(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        TaskResponseDTO taskResponseDTO=new TaskResponseDTO();
        taskResponseDTO.setId(task.id());
        taskResponseDTO.setTitle(task.title());
        taskResponseDTO.setDescription(task.description());
        taskResponseDTO.setDeadline(LocalDate.parse(task.deadline()));
        taskResponseDTO.setCompleted(task.completed());
        taskResponseDTO.setNotes(notes);
        return ResponseEntity.ok(taskResponseDTO);
    }
    @PostMapping
    public ResponseEntity<TaskDTO> addNewTask(@RequestBody TaskDTO taskDTO) throws DateTimeParseException {
        TaskDTO savedTask=taskService.addTask(taskDTO);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }
    @PutMapping("{id}/update")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable int id,@RequestBody TaskDTO taskdto){
        return ResponseEntity.ok(taskService.updateTask(id,taskdto));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorHandlingDTO> handleError(Exception e){
        if(e instanceof DateTimeParseException){
            return ResponseEntity.badRequest().body(new ErrorHandlingDTO("Invalid Date Format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorHandlingDTO("Internal Server Error"));
    }

}
