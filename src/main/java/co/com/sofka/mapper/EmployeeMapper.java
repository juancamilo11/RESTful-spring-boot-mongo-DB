package co.com.sofka.mapper;

import co.com.sofka.collection.Employee;
import co.com.sofka.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeMapper {

    public Employee fromDTO(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setJobPosition(dto.getPosition());
        return employee;
    }

    public EmployeeDTO fromCollection(Employee collection) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(collection.getId());
        employeeDTO.setName(collection.getName());
        employeeDTO.setPosition(collection.getJobPosition());
        return employeeDTO;
    }

    public List<EmployeeDTO> fromCollectionList(List<Employee> collection) {

        if (collection == null) {
            return null;
        }

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        Iterator iterator = collection.iterator();

        while(iterator.hasNext()) {
            Employee employee = (Employee) iterator.next();
            employeeDTOList.add(fromCollection(employee));
        }

        return employeeDTOList;
    }
}

