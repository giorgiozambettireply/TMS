package com.zambetti.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int fieldOrder;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;
}
