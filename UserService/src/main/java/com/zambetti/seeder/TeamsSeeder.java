package com.zambetti.seeder;

import com.zambetti.service.ManagerWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TeamsSeeder {
    @Autowired
    private ManagerWorkerService service;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            //Manager1
            service.addManagerWorker(4L,2L);
            service.addManagerWorker(5L,2L);
            service.addManagerWorker(6L,2L);

            //Manager2
            service.addManagerWorker(4L,3L);
            service.addManagerWorker(5L,3L);
        };
    }
}
