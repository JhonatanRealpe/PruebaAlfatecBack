/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.alfatec.sihna.service;

import com.alfatec.sihna.bean.Department;
import com.alfatec.sihna.bean.Employee;
import com.alfatec.sihna.config.InfrastructureConfig;
import com.alfatec.sihna.service.IDepartmentService;
import com.alfatec.sihna.service.IEmployeeService;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author equipo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = InfrastructureConfig.class)
public class EmployeeServiceTest {

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IDepartmentService departmentService;

    public EmployeeServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class EmployeeService.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Employee employee = new Employee("firsName", "lastName");
        Employee expResult = new Employee("firsName", "lastName");

        Employee result = this.employeeService.create(employee);

        assertThat(result, CoreMatchers.instanceOf(Employee.class));
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getLasName(), result.getLasName());

        this.employeeService.delete(result.getId());
    }

    /**
     * Test of byDept method, of class EmployeeService.
     */
    @Test
    public void testByDept() {
        System.out.println("byDept");
        Department deptResult = new Department();
        deptResult.setName("Department4");
        deptResult = this.departmentService.create(deptResult);

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Employee currEmp = new Employee("firstName" + (i + 1), "lastName" + (i + 1));
            currEmp.setDepartment(deptResult);
            //TODO: no sería mala idea enviar la lista de empleados, para usar una sola sesión.
            employees.add(this.employeeService.create(currEmp));
        }

        assertThat(deptResult, CoreMatchers.instanceOf(Department.class));

        for (int i = 0; i < 10; i++) {
            Employee currEmp = employees.get(i);
            assertThat(currEmp, CoreMatchers.instanceOf(Employee.class));
            assertThat(currEmp.getDepartment(), CoreMatchers.instanceOf(Department.class));
            assertEquals(currEmp.getName(), "firstName" + (i + 1));
            assertEquals(currEmp.getLasName(), "lastName" + (i + 1));
            assertEquals(currEmp.getDepartment().getName(), "Department4");
            this.employeeService.delete(currEmp.getId());
        }

        this.departmentService.delete(deptResult.getId());
    }

    /**
     * Test of byIdAndDeptId method, of class EmployeeService.
     */
    @Test
    public void testByIdAndDeptId() {
        System.out.println("byIdAndDeptId");
        Department deptResult = new Department();
        deptResult.setName("Department3");
        deptResult = this.departmentService.create(deptResult);

        Employee empResult = new Employee("firstName", "lastName");
        empResult.setDepartment(deptResult);
        empResult = this.employeeService.create(empResult);

        Employee expEmp = this.employeeService.byIdAndDeptId(empResult.getId(), deptResult.getId());

        assertThat(expEmp, CoreMatchers.instanceOf(Employee.class));
        assertThat(expEmp.getDepartment(), CoreMatchers.instanceOf(Department.class));
        assertEquals(expEmp.getName(), "firstName");
        assertEquals(expEmp.getLasName(), "lastName");
        assertEquals(expEmp.getDepartment().getName(), "Department3");

        this.employeeService.delete(empResult.getId());
        this.departmentService.delete(deptResult.getId());
    }

    /**
     * Test of deleteByIdAndDeptId method, of class EmployeeService.
     */
    @Test
    public void testDeleteByIdAndDeptId() {
        System.out.println("deleteByIdAndDeptId");
        Department deptResult = new Department();
        deptResult.setName("Department5");
        deptResult = this.departmentService.create(deptResult);

        Employee empResult = new Employee("firstName", "lastName");
        empResult.setDepartment(deptResult);
        empResult = this.employeeService.create(empResult);

        Employee expEmp = this.employeeService.byIdAndDeptId(empResult.getId(), deptResult.getId());

        assertThat(expEmp, CoreMatchers.instanceOf(Employee.class));
        assertThat(expEmp.getDepartment(), CoreMatchers.instanceOf(Department.class));
        assertEquals(expEmp.getName(), "firstName");
        assertEquals(expEmp.getLasName(), "lastName");
        assertEquals(expEmp.getDepartment().getName(), "Department5");

        boolean result = employeeService.deleteByIdAndDeptId(expEmp.getId(), deptResult.getId());
        assertEquals(result, true);

        this.departmentService.delete(deptResult.getId());
    }

    /**
     * Test of update method, of class EmployeeService.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Employee employee = new Employee("firsName", "lastName");
        Employee expResult = new Employee("firsNameNew", "lastNameNew");

        Employee result = this.employeeService.create(employee);

        result.setName("firsNameNew");
        result.setLastName("lastNameNew");

        Employee updated = this.employeeService.update(employee, employee.getId());

        assertThat(result, CoreMatchers.instanceOf(Employee.class));
        assertThat(updated, CoreMatchers.instanceOf(Employee.class));
        assertEquals(updated.getName(), expResult.getName());
        assertEquals(updated.getLasName(), expResult.getLasName());

        this.employeeService.delete(result.getId());
    }
}
