package com.zambetti.repository;

import com.zambetti.entity.ManagerWorker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerWorkerRepository extends JpaRepository<ManagerWorker, Long> {
    List<ManagerWorker> findManagerWorkerByWorkerId(Long workerId);
    List<ManagerWorker> findManagerWorkerByManagerId(Long managerId);
    List<ManagerWorker> findByWorkerIdAndManagerId(Long workerId, Long managerId);
}