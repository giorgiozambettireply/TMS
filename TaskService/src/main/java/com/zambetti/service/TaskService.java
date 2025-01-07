package com.zambetti.service;

import com.zambetti.entity.Task;
import com.zambetti.entity.WorkerTask;
import com.zambetti.repository.TaskRepository;
import com.zambetti.repository.WorkerTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired

    private WorkerTaskRepository workerTaskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findAllByEmployeeId(Long employeeId) {
        List<WorkerTask> workerTasks = workerTaskRepository.findAllByWorkerId(employeeId);
        List<Long> taskIds = workerTasks.stream()
                .map(WorkerTask::getTaskId)
                .collect(Collectors.toList());

        return taskRepository.findAllById(taskIds);
    }

    public List<Task> findAllByManagerId(Long managerId) {
        return taskRepository.findAllByManagerId(managerId);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Task update(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setManagerId(taskDetails.getManagerId());
        task.setDeadline(taskDetails.getDeadline());
        task.setDescription(taskDetails.getDescription());
        return taskRepository.save(task);
    }
}
