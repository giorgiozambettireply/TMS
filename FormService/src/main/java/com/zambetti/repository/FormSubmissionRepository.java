package com.zambetti.repository;

import com.zambetti.entity.Form;
import com.zambetti.entity.FormSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Long> {

    List<FormSubmission> findAllByFormTaskId(Long formTaskId);

    @Query("SELECT f FROM FormSubmission f LEFT JOIN FETCH f.fieldSubmissions WHERE f.id = :id")
    Optional<FormSubmission> findByIdWithFields(@Param("id") Long id);
}