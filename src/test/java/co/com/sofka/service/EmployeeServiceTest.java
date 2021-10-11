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
        Employee dato1 = new Employee();
        dato1.setId("1");
        dato1.setName("Camilo Cardona");
        dato1.setJobPosition("Administrador");
        Employee dato2 = new Employee();
        dato1.setId("2");
        dato1.setName("Johana Villada");
        dato1.setJobPosition("Secretaria");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(dato1);
        employeeList.add(dato2);

        //Act
        Mockito.when(this.employeeRepositoryMock.findAll()).thenReturn(employeeList);

        //Assert
        List<EmployeeDTO> resultado = this.employeeService.getAllEMployees();
        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(dato1.getName(), resultado.get(0).getName());
        Assertions.assertEquals(dato2.getName(), resultado.get(1).getName());
    }


}