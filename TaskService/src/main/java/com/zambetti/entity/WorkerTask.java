package com.zambetti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "worker_tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workerId;

    private Long taskId;

    @Enumerated(EnumType.STRING)
    private TaskState state;

    public enum TaskState {
        TODO,
        IN_PROGRESS,
        DONE
    }
}