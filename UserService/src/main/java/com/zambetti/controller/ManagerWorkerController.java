package com.zambetti.controller;

import com.zambetti.service.ManagerWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class ManagerWorkerController {
    @Autowired
    private ManagerWorkerService managerWorkerService;

    @GetMapping("/{workerId}/managers")
    public List<Long> getManagersIdsOf(@PathVariable(name = "workerId") Long workerId) {
         return managerWorkerService.getManagerIdsForWorker(workerId);
    }

    @GetMapping("/{managerId}/workers")
    public List<Long> getWorkersIdsOf(@PathVariable(name = "managerId") Long managerId) {
        return managerWorkerService.getWorkerIdsForManager(managerId);
    }

    @PostMapping("/team/{managerId}/add/{workerId}")
    public ResponseEntity<String> addToTeam(@PathVariable(name = "workerId") Long workerId, @PathVariable(name = "managerId") Long managerId) {
        managerWorkerService.addManagerWorker(workerId, managerId);
        return ResponseEntity.ok("Relationship added successfully");
    }

    @DeleteMapping("/team/{managerId}/remove/{workerId}")
    public ResponseEntity<String> removeFromTeam(@PathVariable(name = "workerId") Long workerId, @PathVariable(name = "managerId") Long managerId) {
        managerWorkerService.removeManagerWorker(workerId, managerId);
        return ResponseEntity.ok("Relationship removed successfully if present");
    }
}
