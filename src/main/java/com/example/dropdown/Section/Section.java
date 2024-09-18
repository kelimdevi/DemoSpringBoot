package com.example.dropdown.Section;


import com.example.dropdown.Dept.Department;
import jakarta.persistence.*;

@Entity
public class Section {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String sectionName;
    private String Department;

    @ManyToOne
    @JoinColumn(name="department_id",referencedColumnName = "id")

    private Department department;

    @Column(insertable=false, updatable=false)
    private String SectionName;

    public Section(){}
    public Section(String section_name, Department department) {
        this.sectionName = section_name;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSection_name() {
        
        return SectionName;
    }

    public void setSection_name(String section_name) {
        SectionName = section_name;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }
}
