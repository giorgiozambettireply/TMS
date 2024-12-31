package com.zambetti.service;

import com.zambetti.entity.Task;
import com.zambetti.entity.WorkerTask;
import com.zambetti.repository.WorkerTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerTaskService {

    @Autowired
    private WorkerTaskRepository workerTaskRepository;

    public WorkerTask assignWorker(Task task, Long workerId) {
        WorkerTask workerTask = new WorkerTask();
        workerTask.setTaskId(task.getId());
        workerTask.setWorkerId(workerId);
        workerTask.setState(WorkerTask.TaskState.TODO);

        return workerTaskRepository.save(workerTask);
    }

    public void deassignWorker(Task task, Long workerId) {
        WorkerTask workerTask = workerTaskRepository.findByWorkerIdAndTaskId(workerId, task.getId())
                .orElseThrow(() -> new RuntimeException("WorkerTask not found with id " + task.getId()));
        workerTaskRepository.delete(workerTask);
    }

    public WorkerTask changeStatus(Task task, WorkerTask.TaskState state) {
        WorkerTask workerTask = workerTaskRepository.findById(task.getId())
                .orElseThrow(() -> new RuntimeException("WorkerTask not found with id " + task.getId()));
        workerTask.setState(state);
        return workerTaskRepository.save(workerTask);
    }
}
