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
    public List<Long> getManagersIds(@PathVariable(name = "workerId") Long workerId) {
         return managerWorkerService.getManagerIdsForWorker(workerId);
    }

    @GetMapping("/{managerId}/workers")
    public List<Long> getWorkersIds(@PathVariable(name = "managerId") Long managerId) {
        return managerWorkerService.getWorkerIdsForManager(managerId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRelationship(@RequestParam(name = "workerId") Long workerId, @RequestParam(name = "managerId") Long managerId) {
        managerWorkerService.addManagerWorker(workerId, managerId);
        return ResponseEntity.ok("Relationship added successfully");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeRelationship(@RequestParam(name = "workerId") Long workerId, @RequestParam(name = "managerId") Long managerId) {
        managerWorkerService.removeManagerWorker(workerId, managerId);
        return ResponseEntity.ok("Relationship removed successfully if present");
    }
}
