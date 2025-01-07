package com.zambetti.controller;

import com.zambetti.entity.Task;
import com.zambetti.entity.WorkerTask;
import com.zambetti.service.TaskService;
import com.zambetti.service.WorkerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private WorkerTaskService workerTaskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/employee/{employeeId}") //TODO: get employeeId from token
    public List<Task> getAllTasksByEmployeeId(@PathVariable(name = "employeeId") Long employeeId) {
        return taskService.findAllByEmployeeId(employeeId);
    }

    @GetMapping("/manager/{managerId}") //TODO: get managerId from token
    public List<Task> getAllTasksByManagerId(@PathVariable(name = "managerId") Long managerId) {
        return taskService.findAllByManagerId(managerId);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(name = "id") Long id) {
        return taskService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.save(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(name = "id") Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.update(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskId}/assignWorker/{workerId}")
    public ResponseEntity<WorkerTask> assignWorker(@PathVariable(name = "taskId") Long taskId, @PathVariable(name = "workerId") Long workerId, @RequestBody String workerName) {
        Optional<Task> task = taskService.findById(taskId);

        if (task.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        WorkerTask createdWorkerTask = workerTaskService.assignWorker(task.get(), workerId, workerName);
        return new ResponseEntity<>(createdWorkerTask, HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}/assignedWorkers")
    public ResponseEntity<List<WorkerTask>> getAssignedWorkers(@PathVariable(name = "taskId") Long taskId) {
        Optional<Task> task = taskService.findById(taskId);

        if (task.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<WorkerTask> assignedWorkers = workerTaskService.getAssignedWorkers(task.get());
        return new ResponseEntity<>(assignedWorkers, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}/deassignWorker/{workerId}")
    public ResponseEntity<Void> deassignWorker(@PathVariable(name = "taskId") Long taskId, @PathVariable(name = "workerId") Long workerId) {
        Optional<Task> task = taskService.findById(taskId);

        if (task.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        workerTaskService.deassignWorker(task.get(), workerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{taskId}/changeStatus/{state}")
    public ResponseEntity<WorkerTask> changeWorkerTaskStatus(@PathVariable(name = "taskId") Long taskId, @PathVariable(name = "state") WorkerTask.TaskState state) {
        Optional<Task> task = taskService.findById(taskId);

        if (task.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        WorkerTask createdWorkerTask = workerTaskService.changeStatus(task.get(), state);
        return new ResponseEntity<>(createdWorkerTask, HttpStatus.CREATED);
    }
}