package com.zambetti.repository;

import com.zambetti.entity.WorkerTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerTaskRepository extends JpaRepository<WorkerTask, Long> {
    Optional<WorkerTask> findByWorkerIdAndTaskId(Long workerId, Long taskId);

    List<WorkerTask> findAllByTaskId(Long taskId);
}