/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alfatec.sihna.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import com.alfatec.sihna.bean.Employee;

/**
 *
 * @author equipo
 */
public class EmployeeSpec {

    public static Specification<Employee> byDept(final Long deptId) {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get("department"), deptId);
            }

        };
    }

    public static Specification<Employee> byId(final Long empId) {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get("id"), empId);
            }

        };
    }

}
