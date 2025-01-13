package com.zambetti.service;

import com.zambetti.entity.*;
import com.zambetti.repository.FieldRepository;
import com.zambetti.repository.FormRepository;
import com.zambetti.repository.FormSubmissionRepository;
import com.zambetti.repository.FormTaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormSubmissionRepository formSubmissionRepository;

    @Autowired
    private FormTaskRepository formTaskRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public Form createForm(Form form) {
        if(form.getFields() != null)
            for (Field field : form.getFields())
                field.setForm(form);

        return formRepository.save(form);
    }

    public FormTask attachForm(Form form, Long taskId) {
        FormTask formTask = new FormTask();
        formTask.setFormId(form.getId());
        formTask.setTaskId(taskId);

        return formTaskRepository.save(formTask);
    }

    @Transactional
    public Form getFormById(Long id) {
        return formRepository.findByIdWithFields(id).orElse(null);
    }

    @Transactional
    public FormSubmission getFormSubmissionById(Long id) {
        return formSubmissionRepository.findByIdWithFields(id).orElse(null);
    }

    public Form updateForm(Long id, Form formDetails) {
        Form form = formRepository.findById(id).orElse(null);
        if (form != null) {
            form.setTitle(formDetails.getTitle());
            form.setManagerId(formDetails.getManagerId());
            form.setFields(formDetails.getFields());
            return formRepository.save(form);
        }
        return null;
    }

    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }

    public List<Form> getTaskForms(Long taskId) {
        var formTasks = formTaskRepository.findAllByTaskId(taskId);
        return formTasks.stream()
                .map(formTask -> formRepository.findById(formTask.getFormId()).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }

    public List<FormTask> getFormTasks(Long taskId) {
        return formTaskRepository.findAllByTaskId(taskId);
    }

    public Optional<FormTask> getFormTask(Long formTaskId) {
        return formTaskRepository.findById(formTaskId);
    }

    public List<Form> getManagerForms(Long managerId) {
        var formTasks = formRepository.findAllByManagerId(managerId);
        return formTasks.stream()
                .map(formTask -> formRepository.findById(formTask.getId()).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }


    public FormSubmission submitForm(FormSubmission formSubmission) {
        if(formSubmission.getFieldSubmissions() != null)
            for (FieldSubmission field : formSubmission.getFieldSubmissions())
                field.setFormSubmission(formSubmission);

        return formSubmissionRepository.save(formSubmission);
    }

    public List<FormSubmission> getAllFormSubmissions() {
        return formSubmissionRepository.findAll();
    }

    public List<FormSubmission> getTaskFormSubmissions(Long taskId) {
       return formSubmissionRepository.findAllByFormTaskId(taskId);
    }

}

