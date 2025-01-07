package com.zambetti.seeder;

import com.zambetti.entity.Form;
import com.zambetti.entity.FormSubmission;
import com.zambetti.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FormsSeeder {
    @Autowired
    private FormService service;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            Form form = new Form();
            form.setManagerId(2L);
            form.setTitle("Form 1");

            var createdForm = service.createForm(form);
            var formTask = service.attachForm(createdForm, 1L);

            FormSubmission submission = new FormSubmission();
            submission.setFormTask(formTask);
            submission.setWorkerId(4L);

            service.submitForm(submission);
        };
    }
}
