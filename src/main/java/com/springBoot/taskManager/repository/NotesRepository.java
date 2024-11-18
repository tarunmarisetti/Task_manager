package com.springBoot.taskManager.repository;

import com.springBoot.taskManager.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<NoteEntity,Integer> {
    List<NoteEntity> findByTaskId(Integer taskId);
}
