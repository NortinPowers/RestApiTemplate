package by.tms.rest.template.controller;

import static by.tms.rest.template.utils.ResponseUtils.CREATION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.DATA_INTEGRITY_VIOLATION_EXCEPTION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.HTTP_NOT_READABLE_EXCEPTION_MESSAGE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
class CityControllerCreateTest {

    private static final String CITY = "city";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {
        String requestBody = """
                {
                "name" : "London",
                "info" : "is the capital of Great Britain"
                }
                """;
        mockMvc.perform(post("/city")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("message").value(String.format(CREATION_MESSAGE, CITY)));
        mockMvc.perform(post("/city")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("message").value(DATA_INTEGRITY_VIOLATION_EXCEPTION_MESSAGE))
                    .andExpect(jsonPath("type").value("DataIntegrityViolationException"));
        String requestBodyNoName = """
                {
                "info" : "capital and largest city of Germany"
                }
                """;
        mockMvc.perform(post("/city")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBodyNoName))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(content().json("{\"errors\":[\"The 'name' field is required\"],\"type\":\"The transmitted data did not pass verification\"}"));
        String requestBodyNoInfo = """
                {
                "name" : "Berlin"
                }
                """;
        mockMvc.perform(post("/city")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBodyNoInfo))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("message").value(String.format(CREATION_MESSAGE, CITY)));
        mockMvc.perform(post("/cities"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        String requestBodyInvalidField = """
                {
                "names" : "Istanbul",
                "info" : "former Constantinople, ancient Byzantium, the largest city and the main seaport of Turkey"
                }
                """;
        mockMvc.perform(post("/city")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBodyInvalidField))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(content().json("{\"errors\":[\"The 'name' field is required\"],\"type\":\"The transmitted data did not pass verification\"}"));
        String requestBodyUnnecessaryField = """
                {
                "name" : "Istanbul",
                "info" : "former Constantinople, ancient Byzantium, the largest city and the main seaport of Turkey"
                "color" : "red"
                }
                """;
        mockMvc.perform(post("/city")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBodyUnnecessaryField))
                    .andDo(print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("message").value(HTTP_NOT_READABLE_EXCEPTION_MESSAGE))
                    .andExpect(jsonPath("type").value("HttpMessageNotReadableException"));
    }
}
