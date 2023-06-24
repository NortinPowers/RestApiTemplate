package by.tms.rest.template.controller;

import static by.tms.rest.template.utils.ResponseUtils.NOT_FOUND_EXCEPTION_MESSAGE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CityControllerGetTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllPositive() throws Exception {
        mockMvc.perform(get("/city"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().json(
                       "[{\"id\":1,\"name\":\"Minsk\",\"info\":\"is the capital and the largest city of Belarus, located on the Svislach and the now subterranean Niamiha rivers.\"},"
                               + "{\"id\":2,\"name\":\"New York\",\"info\":\"is the most populous city in the United States.\"},"
                               + "{\"id\":3,\"name\":\"Warsaw\",\"info\":\"is the capital and largest city of Poland.\"},"
                               + "{\"id\":4,\"name\":\"Boston\",\"info\":null}]"));
    }

    @Test
    void getAllNegative() throws Exception {
        mockMvc.perform(get("/cities"))
               .andDo(print())
               .andExpect(status().isNotFound());
    }

    @Test
    void getOnePositive() throws Exception {
        String response = """
                {
                "id" : 1,
                "name" : "Minsk",
                "info" : "is the capital and the largest city of Belarus, located on the Svislach and the now subterranean Niamiha rivers."
                }
                """;
        mockMvc.perform(get("/city/1"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().json(response));
    }

    @Test
    void getOneNegativeNotFound() throws Exception {
        mockMvc.perform(get("/city/40"))
               .andDo(print())
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("message").value(NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Test
    void getOneNegativeIncorrectPath() throws Exception {
        mockMvc.perform(get("/cities/1"))
               .andDo(print())
               .andExpect(status().isNotFound());
    }
}
