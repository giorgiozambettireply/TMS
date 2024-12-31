package com.zambetti.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FieldSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stringValue;

    @ManyToOne
    @JoinColumn(name = "form_submission_id")
    private FormSubmission formSubmission;
}