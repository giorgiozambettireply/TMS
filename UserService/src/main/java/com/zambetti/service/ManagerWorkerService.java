package com.zambetti.service;

import com.zambetti.entity.ManagerWorker;
import com.zambetti.repository.ManagerWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerWorkerService {
    @Autowired
    private ManagerWorkerRepository repository;

    public void addManagerWorker(Long workerId, Long managerId) {
        ManagerWorker managerWorker = new ManagerWorker();
        managerWorker.setWorkerId(workerId);
        managerWorker.setManagerId(managerId);
        repository.save(managerWorker);
    }

    public List<Long> getManagerIdsForWorker(Long workerId) {
        List<ManagerWorker> employees = repository.findManagerWorkerByWorkerId(workerId);
        return employees.stream().map(ManagerWorker::getManagerId).collect(Collectors.toList());
    }

    public List<Long> getWorkerIdsForManager(Long managerId) {
        List<ManagerWorker> employees = repository.findManagerWorkerByManagerId(managerId);
        return employees.stream().map(ManagerWorker::getWorkerId).collect(Collectors.toList());
    }

    public void removeManagerWorker(Long workerId, Long managerId) {
        List<ManagerWorker> employees = repository.findByWorkerIdAndManagerId(workerId, managerId);
        repository.deleteAll(employees);
    }
}