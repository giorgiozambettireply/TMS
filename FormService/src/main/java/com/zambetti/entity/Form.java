package com.zambetti.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long managerId;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<Field> fields;
}