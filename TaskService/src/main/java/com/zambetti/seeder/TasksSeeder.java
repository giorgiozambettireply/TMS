package com.zambetti.seeder;

import com.zambetti.entity.Task;
import com.zambetti.service.TaskService;
import com.zambetti.service.WorkerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class TasksSeeder {
    @Autowired
    private TaskService service;

    @Autowired
    private WorkerTaskService workerTaskService;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            Task task = new Task();
            task.setDeadline(LocalDateTime.now().plusDays(5));
            task.setDescription("This is a task");
            task.setManagerId(2L);
            task.setManagerName("Manager1");
            service.save(task);

            workerTaskService.assignWorker(task, 4L, "Employee1");
            workerTaskService.assignWorker(task, 5L, "Employee2");
        };
    }
}
