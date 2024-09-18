package com.example.dropdown.Dept;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping("/department")
public class DepartController {

    @Autowired
    private DeptRepo deptRepo;

    @PostMapping
    public ResponseEntity<String> addDepartment(@RequestBody Department department)
    {
        try {
            deptRepo.save(department);
            return ResponseEntity.ok().body("Department added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping
    public ResponseEntity<List<Department>> getDepartments() {
        try {
            List<Department> departments = deptRepo.findAll();
            return ResponseEntity.ok(departments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
