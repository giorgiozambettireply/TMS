package com.zambetti.repository;

import com.zambetti.entity.FormSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Long> {

    List<FormSubmission> findAllByFormTaskId(Long formTaskId);
}