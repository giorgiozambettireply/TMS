package com.zambetti.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String type;
    private int fieldOrder;
    private boolean required;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "form_id")
    private Form form;
}
