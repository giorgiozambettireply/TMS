package com.zambetti.repository;

import com.zambetti.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findAllByManagerId(Long managerId);
}