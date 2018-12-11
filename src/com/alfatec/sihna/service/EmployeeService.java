package com.alfatec.sihna.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfatec.sihna.bean.Department;
import com.alfatec.sihna.bean.Employee;
import com.alfatec.sihna.repository.EmployeeRepository;
import com.alfatec.sihna.repository.EmployeeSpec;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    IDepartmentService departmentService;

    @Override
    public Employee create(Employee employee) {
        if (employee.getDepartment() != null) {
            Department department = this.departmentService.get(employee.getDepartment().getId());
            employee.setDepartment(department);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee get(Long employeeId) {
        return employeeRepository.findOne(employeeId);
    }

    @Override
    public Employee update(Employee employee, Long employeeId) {
        Employee oldEmp = this.employeeRepository.findOne(employeeId);
        if (oldEmp != null) {
            oldEmp.setName(employee.getName());
            oldEmp.setLastName(employee.getLasName());
            if (employee.getDepartment() != null) {
                Department department = this.departmentService.get(employee.getDepartment().getId());
                oldEmp.setDepartment(department);
            }
        }
        return employeeRepository.save(oldEmp);
    }

    @Override
    public void delete(Long employeeId) {
        employeeRepository.delete(employeeId);
    }

    @Override
    public List<Employee> list() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public List<Employee> byDept(Long deptId) {
        Specification<Employee> spec;
        spec = Specifications.where(EmployeeSpec.byDept(deptId));
        List<Employee> employees = this.employeeRepository.findAll(spec);
        if (employees != null && !employees.isEmpty()) {
            return employees;
        } else {
            return null;
        }
    }

    @Override
    public Employee byIdAndDeptId(Long employeeId, Long deptId) {
        Specification<Employee> spec;
        spec = Specifications.where(EmployeeSpec.byDept(deptId))
                .and(EmployeeSpec.byId(employeeId));
        Employee employee = this.employeeRepository.findOne(spec);
        if (employee != null) {
            return employee;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteByIdAndDeptId(Long employeeId, Long deptId) {
        Specification<Employee> spec;
        spec = Specifications.where(EmployeeSpec.byDept(deptId))
                .and(EmployeeSpec.byId(employeeId));
        Employee employee = this.employeeRepository.findOne(spec);
        if (employee != null) {
            this.employeeRepository.delete(employee);
            return true;
        } else {
            return false;
        }
    }

}
