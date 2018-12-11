/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.alfatec.sihna.service;

import com.alfatec.sihna.bean.Department;
import com.alfatec.sihna.config.InfrastructureConfig;
import com.alfatec.sihna.service.IDepartmentService;

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
public class DepartmentServiceTest {

    @Autowired
    IDepartmentService departmentService;

    public DepartmentServiceTest() {
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
     * Test of create method, of class DepartmentService.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Department department = new Department("Department1");
        Department expResult = new Department();
        expResult.setName("Department1");
        Department result = this.departmentService.create(department);

        assertThat(result, CoreMatchers.instanceOf(Department.class));
        assertEquals(expResult.getName(), result.getName());

        this.departmentService.delete(result.getId());
    }

    /**
     * Test of get method, of class DepartmentService.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Department expResult = new Department();
        expResult.setName("Department2");
        expResult = this.departmentService.create(expResult);

        Department result = this.departmentService.get(expResult.getId());

        assertThat(result, CoreMatchers.instanceOf(Department.class));
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getName(), result.getName());

        this.departmentService.delete(result.getId());
    }
}
