package co.com.sofka.service;

import co.com.sofka.collection.Employee;
import co.com.sofka.dto.EmployeeDTO;
import co.com.sofka.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @MockBean
    private EmployeeRepository employeeRepositoryMock;

    @Autowired
    private EmployeeService employeeService;

    @Test
    @DisplayName("Testing the method getAllEmployees")
    void getAllEMployees() {

        //Arrange
        Employee employee1 = new Employee();
        employee1.setId("1");
        employee1.setName("Camilo Cardona");
        employee1.setJobPosition("Administrador");
        Employee employee2 = new Employee();
        employee2.setId("2");
        employee2.setName("Johana Villada");
        employee2.setJobPosition("Secretaria");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        //Act
        Mockito.when(this.employeeRepositoryMock.findAll()).thenReturn(employeeList);

        //Assert
        List<EmployeeDTO> result = this.employeeService.getAllEMployees();
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(employee1.getName(), result.get(0).getName());
        Assertions.assertEquals(employee2.getName(), result.get(1).getName());
    }

    @Test
    @DisplayName("Testing the method getEmployeeById")
    void getEmployeeById() {

        //Arrange
        Employee employee = new Employee();
        employee.setId("1");
        employee.setName("Camilo Cardona");
        employee.setJobPosition("Administrador");

        //Act
        Mockito.when(this.employeeRepositoryMock.findById("1")).thenReturn(Optional.of(employee));

        //Assert
        EmployeeDTO result = this.employeeService.getEmployeeById("1");
        Assertions.assertEquals(employee.getName(), result.getName());
    }

}