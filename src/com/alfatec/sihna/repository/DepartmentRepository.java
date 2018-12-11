package com.alfatec.sihna.repository;

import org.springframework.data.repository.CrudRepository;

import com.alfatec.sihna.bean.Department;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentRepository extends CrudRepository<Department, Long>,
        JpaSpecificationExecutor<Department> {

    @Override
    List<Department> findAll(Specification<Department> spec);
}
