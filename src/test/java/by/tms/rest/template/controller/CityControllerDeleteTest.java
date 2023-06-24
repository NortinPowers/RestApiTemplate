package by.tms.rest.template.controller;

import static by.tms.rest.template.constant.TestConstants.CITY;
import static by.tms.rest.template.utils.ResponseUtils.DELETION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.NOT_FOUND_EXCEPTION_MESSAGE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class CityControllerDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deletePositive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/city/4"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("message").value(String.format(DELETION_MESSAGE, CITY)));
    }

    @Test
    void deleteNegativeNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/city/40"))
               .andDo(print())
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("message").value(NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Test
    void deleteNegativeIncorrectPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cities/4"))
               .andDo(print())
               .andExpect(status().isNotFound());
    }
}
