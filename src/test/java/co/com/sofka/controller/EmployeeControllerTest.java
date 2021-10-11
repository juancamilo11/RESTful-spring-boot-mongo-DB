package co.com.sofka.controller;

import co.com.sofka.collection.Employee;
import co.com.sofka.dto.EmployeeDTO;
import co.com.sofka.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET / employees success")
    public void findAll() throws Exception {
        //setup mock service
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId("1");
        employeeDTO1.setName("Jorge Ramirez");
        employeeDTO1.setPosition("Gerente");

        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setId("2");
        employeeDTO2.setName("Pedro Contreras");
        employeeDTO2.setPosition("Vicepresidente");

        List<EmployeeDTO> lista = new ArrayList<EmployeeDTO>();
        lista.add(employeeDTO1);
        lista.add(employeeDTO2);
        Mockito.when(employeeService.getAllEMployees()).thenReturn(lista);

        //Execute GET Request
        mockMvc.perform(get("/employees"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("Jorge Ramirez")))
                .andExpect(jsonPath("$[0].position", is("Gerente")))
                .andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[1].name", is("Pedro Contreras")))
                .andExpect(jsonPath("$[1].position", is("Vicepresidente")));
    }

    @Test
    @DisplayName("POST /employees/create success")
    public void create() throws Exception {
        // Setup our mocked service
        EmployeeDTO datoPost = new EmployeeDTO();
        datoPost.setName("Jorge Ramirez");
        datoPost.setPosition("Gerente");

        EmployeeDTO datoReturn = new EmployeeDTO();
        datoReturn.setId("2");
        datoReturn.setName("Jorge Ramirez");
        datoReturn.setPosition("Gerente");

        Mockito.when(employeeService.save(datoPost)).thenReturn(datoReturn);

        // Execute the POST request
        mockMvc.perform(post("/employee/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(datoPost)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is("2")))
                .andExpect(jsonPath("$.name", is("Jorge Ramirez")))
                .andExpect(jsonPath("$.position", is("Gerente")));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}