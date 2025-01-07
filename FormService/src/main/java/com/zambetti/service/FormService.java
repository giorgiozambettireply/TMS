package com.zambetti.service;

import com.zambetti.entity.Form;
import com.zambetti.entity.FormSubmission;
import com.zambetti.entity.FormTask;
import com.zambetti.repository.FormRepository;
import com.zambetti.repository.FormSubmissionRepository;
import com.zambetti.repository.FormTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormSubmissionRepository formSubmissionRepository;

    @Autowired
    private FormTaskRepository formTaskRepository;

    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    public FormTask attachForm(Form form, Long taskId) {
        FormTask formTask = new FormTask();
        formTask.setFormId(form.getId());
        formTask.setTaskId(taskId);

        return formTaskRepository.save(formTask);
    }

    public Form getFormById(Long id) {
        return formRepository.findById(id).orElse(null);
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

    public List<Form> getManagerForms(Long managerId) {
        var formTasks = formRepository.findAllByManagerId(managerId);
        return formTasks.stream()
                .map(formTask -> formRepository.findById(formTask.getId()).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }


    public FormSubmission submitForm(FormSubmission formSubmission) {
        return formSubmissionRepository.save(formSubmission);
    }

    public List<FormSubmission> getAllFormSubmissions() {
        return formSubmissionRepository.findAll();
    }

    public FormSubmission getFormSubmissionById(Long id) {
        return formSubmissionRepository.findById(id).orElse(null);
    }

    public List<FormSubmission> getTaskFormSubmissions(Long taskId) {
       return formSubmissionRepository.findAllByFormTaskId(taskId);
    }

}

