package com.alfatec.sihna.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.alfatec.sihna.bean.Department;
import com.alfatec.sihna.service.IDepartmentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class DepartmentController {

    @Autowired
    public IDepartmentService departmentService;

    @PostMapping("/department")
    public Department create(@RequestBody Department department) {
        return departmentService.create(department);
    }

    @GetMapping("department/byName/{name}")
    public ResponseEntity<Department> byName(@PathVariable(name = "name") String deptName) {
        Department department = this.departmentService.byName(deptName);
        if (department != null) {
            return new ResponseEntity(department, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/departments")
    public List<Department> all() {
        return departmentService.list();
    }

    @GetMapping("/department/empoyees/{id}")
    public String lala(@PathVariable Long departmentId) {
        return "lala";
    }
}
