package com.alfatec.sihna.service;

import java.util.List;

import com.alfatec.sihna.bean.Department;
import com.alfatec.sihna.bean.Employee;

public interface IDepartmentService {

    public Department create(Department department);

    public Department get(Long accountId);

    public Department byName(String name);

    public List<Employee> getEmployeesByDeptName(String name);

    public Department update(Department account, Long accountId);

    public void delete(Long accountId);

    public List<Department> list();
}
