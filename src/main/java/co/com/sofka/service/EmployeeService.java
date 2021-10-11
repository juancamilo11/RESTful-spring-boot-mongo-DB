package co.com.sofka.service;

import co.com.sofka.collection.Employee;
import co.com.sofka.dto.EmployeeDTO;
import co.com.sofka.mapper.EmployeeMapper;
import co.com.sofka.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeMapper mapper = new EmployeeMapper();

    public List<EmployeeDTO> getAllEMployees() {
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        return mapper.fromCollectionList(employeeList);
    }

    public EmployeeDTO getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee with id " + id + " was not found."));
        return mapper.fromCollection(employee);
    }

    public EmployeeDTO save(EmployeeDTO empleadoDTO) {
        Employee empleado = mapper.fromDTO(empleadoDTO);
        return mapper.fromCollection(employeeRepository.save(empleado));
    }

    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        Employee empleado = mapper.fromDTO(employeeDTO);
        employeeRepository.findById(empleado.getId()).orElseThrow(() -> new RuntimeException("Employee with id" + employeeDTO.getId() + " was not found."));
        return mapper.fromCollection(employeeRepository.save(empleado));
    }

    public void delete(String id) {
        employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee with id" + id + " was not found."));
        employeeRepository.deleteById(id);
    }

}
