package com.springBoot.taskManager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="deadline")
    private LocalDate deadline;

    @Column(name = "completed")
    private boolean completed;

    // One-to-Many Relationship with NoteEntity
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NoteEntity> notes;

    public TaskEntity(int id, String title, String description, LocalDate parse, Boolean completed) {
    }
}
