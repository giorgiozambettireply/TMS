package com.zambetti.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FieldSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stringValue;
    private Long fieldId;
    private int fieldOrder;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "form_submission_id")
    private FormSubmission formSubmission;
}