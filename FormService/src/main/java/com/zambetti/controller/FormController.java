package com.zambetti.controller;

import com.zambetti.entity.Form;
import com.zambetti.entity.FormSubmission;
import com.zambetti.entity.FormTask;
import com.zambetti.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    @Autowired
    private FormService formService;

    @PostMapping
    public ResponseEntity<Form> createForm(@RequestBody Form form) {
        Form savedForm = formService.createForm(form);
        return ResponseEntity.ok(savedForm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable(name = "id") Long id) {
        Form form = formService.getFormById(id);
        return form != null ? ResponseEntity.ok(form) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable(name = "id") Long id, @RequestBody Form formDetails) {
        Form updatedForm = formService.updateForm(id, formDetails);
        return updatedForm != null ? ResponseEntity.ok(updatedForm) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable(name = "id") Long id) {
        formService.deleteForm(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Form>> getTaskForms(@PathVariable(name = "taskId") Long taskId) {
        List<Form> forms = formService.getTaskForms(taskId);
        return ResponseEntity.ok(forms);
    }

    @GetMapping("/formTask/{taskId}")
    public ResponseEntity<List<FormTask>> getFormTasks(@PathVariable(name = "taskId") Long taskId) {
        return ResponseEntity.ok(formService.getFormTasks(taskId));
    }

    @GetMapping("/formTask/formTask/{formTaskId}")
    public ResponseEntity<FormTask> getFormTask(@PathVariable(name = "formTaskId") Long formTaskId) {
        return ResponseEntity.ok(formService.getFormTask(formTaskId).get());
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<Form>> getManagerForms(@PathVariable(name = "managerId") Long managerId) {
        List<Form> forms = formService.getManagerForms(managerId);
        return ResponseEntity.ok(forms);
    }

    @PostMapping("/submission")
    public ResponseEntity<FormSubmission> submitForm(@RequestBody FormSubmission formSubmission) {
        FormSubmission savedSubmission = formService.submitForm(formSubmission);
        return ResponseEntity.ok(savedSubmission);
    }

    @GetMapping("/submissions")
    public ResponseEntity<List<FormSubmission>> getAllFormSubmissions(@PathVariable(name = "taskId") Long taskId) {
        List<FormSubmission> submissions = formService.getAllFormSubmissions();
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/submissions/task/{taskId}")
    public ResponseEntity<List<FormSubmission>> getTaskFormSubmissions(@PathVariable(name = "taskId") Long taskId) {
        List<FormSubmission> submissions = formService.getTaskFormSubmissions(taskId);
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/submissions/{id}")
    public ResponseEntity<FormSubmission> getFormSubmissionById(@PathVariable(name = "id") Long id) {
        FormSubmission submission = formService.getFormSubmissionById(id);
        return submission != null ? ResponseEntity.ok(submission) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{formId}/attachToTask/{taskId}")
    public ResponseEntity<FormTask> attachFormToTask(@PathVariable(name = "formId") Long formId, @PathVariable(name = "taskId") Long taskId) {
        FormTask formTask = formService.attachForm(formService.getFormById(formId), taskId);
        return ResponseEntity.ok(formTask);
    }
}
