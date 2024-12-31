package com.zambetti.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class FormSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long formTaskId;
    private Long workerId;

    @OneToMany(mappedBy = "formSubmission", cascade = CascadeType.ALL)
    private List<FieldSubmission> fieldSubmissions;
}