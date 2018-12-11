package com.alfatec.sihna.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.alfatec.sihna.bean.Employee;
import com.alfatec.sihna.service.IEmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class EmployeeController {

    @Autowired
    public IEmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.create(employee);
        if (newEmployee != null) {
            return new ResponseEntity(newEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/employee/department/{id}")
    public ResponseEntity<List<Employee>> byDeptId(@PathVariable(name = "id") Long deptId) {
        List<Employee> employees = this.employeeService.byDept(deptId);
        if (employees != null) {
            return new ResponseEntity(employees, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee/bydepartment/{empId}/{deptId}")
    public ResponseEntity<List<Employee>> byIdAndDeptId(
            @PathVariable(name = "empId") Long empId,
            @PathVariable(name = "deptId") Long deptId) {
        Employee employee = this.employeeService.byIdAndDeptId(empId, deptId);
        if (employee != null) {
            return new ResponseEntity(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employee/bydepartment/{empId}/{deptId}")
    public ResponseEntity<List<Employee>> deleteByIdAndDeptId(
            @PathVariable(name = "empId") Long empId,
            @PathVariable(name = "deptId") Long deptId) {
        boolean result = this.employeeService.deleteByIdAndDeptId(empId, deptId);
        if (result) {
            return new ResponseEntity(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees")
    public List<Employee> all() {
        return employeeService.list();
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> update(@PathVariable(name = "id") Long id, @RequestBody Employee employee) {
        Employee updated = employeeService.update(employee, id);
        if (updated != null) {
            return new ResponseEntity(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.FORBIDDEN);
        }
    }
}
