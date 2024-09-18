package com.example.dropdown.Section;


import com.example.dropdown.Dept.DeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sections")
public class SecController {

    @Autowired
    private SecRepo secRepo;

    @Autowired
    private DeptRepo deptRepo;

    @PostMapping
    public Section addSection(@RequestBody Section section)
    {
        return secRepo.save(section);
    }

    @GetMapping
    public List<Section> getAllSections()
    {
        return secRepo.findAll();
    }







}
