package com.zambetti.controller;

import com.zambetti.entity.ManagerWorker;
import com.zambetti.service.ManagerWorkerService;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/employees")
public class ManagerWorkerController {
    @Autowired
    private ManagerWorkerService managerWorkerService;
    @Autowired

    @GetMapping("/{workerId}/managers")
    public List<Long> getManagersIds(@PathVariable(name = "workerId") Long workerId) {
         return managerWorkerService.getManagerIdsForWorker(workerId);
    }

    @GetMapping("/{managerId}/workers")
    public List<Long> getWorkersIds(@PathVariable(name = "managerId") Long managerId) {
        return managerWorkerService.getWorkerIdsForManager(managerId);
    }

    @PostMapping("/add-relationship")
    public ResponseEntity<String> addRelationship(@RequestParam Long workerId, @RequestParam Long managerId) {
        managerWorkerService.addManagerWorker(workerId, managerId);
        return ResponseEntity.ok("Relationship added successfully");
    }

    @DeleteMapping("/remove-relationship")
    public ResponseEntity<String> removeRelationship(@RequestParam Long workerId, @RequestParam Long managerId) {
        managerWorkerService.removeManagerWorker(workerId, managerId);
        return ResponseEntity.ok("Relationship removed successfully");
    }
}
