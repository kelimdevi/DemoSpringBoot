package com.example.dropdown.sections;

import com.example.dropdown.Dept.Department;
import jakarta.persistence.*;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sectionname;
    private String deptname;


    @ManyToOne
    @JoinColumn(name="department_id",nullable = false)
    private Department department;

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", sectionname='" + sectionname + '\'' +
                ", deptname='" + deptname + '\'' +
                ", department=" + department +
                '}';
    }
}

