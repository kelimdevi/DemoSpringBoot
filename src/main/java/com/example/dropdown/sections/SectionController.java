package com.example.dropdown.sections;

import com.example.dropdown.Dept.Department;
import com.example.dropdown.Dept.DeptRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class SectionController {

    @Autowired
    private SecRepo secRepo;

    @Autowired
    private DeptRepo departmentRepo;

    @PostMapping("/section")
    public ResponseEntity<String> addSection(@RequestBody Section section) {

        try
        {
            Department department = departmentRepo.findById(section.getDepartment().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found with id " + section.getDepartment().getId()));
            section.setDepartment(department);
            section.setDeptname(department.getDeptName());
            secRepo.save(section);
            return ResponseEntity.ok().body("Section added successfully");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/section")
    public List<Section> getSections() {

        return secRepo.findAll();
    }
}
