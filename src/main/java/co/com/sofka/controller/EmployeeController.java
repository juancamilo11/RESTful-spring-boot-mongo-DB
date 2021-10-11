package co.com.sofka.controller;

import co.com.sofka.dto.EmployeeDTO;
import co.com.sofka.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return new ResponseEntity(employeeService.getAllEMployees(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO empleadoDTO) {
        return new ResponseEntity(employeeService.save(empleadoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeDTO> update(@RequestBody EmployeeDTO empleadoDTO) {
        if (empleadoDTO.getId() != null) {
            return new ResponseEntity(employeeService.update(empleadoDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            employeeService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
