package com.zambetti.seeder;

import com.zambetti.entity.Field;
import com.zambetti.entity.FieldSubmission;
import com.zambetti.entity.Form;
import com.zambetti.entity.FormSubmission;
import com.zambetti.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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

            Field field = new Field();
            field.setLabel("Text Field");
            field.setType("text");
            field.setFieldOrder(1);

            form.setFields(List.of(field));

            var createdForm = service.createForm(form);
            var formTask = service.attachForm(createdForm, 1L);



            FormSubmission submission = new FormSubmission();
            submission.setFormTask(formTask);
            submission.setWorkerId(4L);

            FieldSubmission fieldSubmission = new FieldSubmission();
            fieldSubmission.setFieldId(field.getId());
            fieldSubmission.setFormSubmission(submission);
            fieldSubmission.setFieldOrder(1);
            fieldSubmission.setStringValue("Submitted Value");

            submission.setFieldSubmissions(List.of(fieldSubmission));
            service.submitForm(submission);

        };
    }
}
