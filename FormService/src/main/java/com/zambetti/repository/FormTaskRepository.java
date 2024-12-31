package com.zambetti.repository;

import com.zambetti.entity.FormTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormTaskRepository extends JpaRepository<FormTask, Long> {
    List<FormTask> findAllByTaskId(Long taskId);
}
