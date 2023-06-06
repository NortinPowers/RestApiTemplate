package by.tms.rest.template.controller;

import static by.tms.rest.template.utils.ResponseUtils.DATA_INTEGRITY_VIOLATION_EXCEPTION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.HTTP_NOT_READABLE_EXCEPTION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.UPDATE_MESSAGE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
class CityControllerUpdateTest {

    public static final String CITY = "city";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void update() throws Exception {
        String requestBody = """
                {
                "name" : "London",
                "info" : "is the capital of Great Britain"
                }
                """;
        this.mockMvc.perform(patch("/city/1")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("message").value(String.format(UPDATE_MESSAGE, CITY)));
        this.mockMvc.perform(patch("/city/2")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("message").value(DATA_INTEGRITY_VIOLATION_EXCEPTION_MESSAGE))
                    .andExpect(jsonPath("type").value("DataIntegrityViolationException"));
        String requestBodyNoInfo = """
                {
                "name" : "Berlin"
                }
                """;
        this.mockMvc.perform(patch("/city/1")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBodyNoInfo))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("message").value(String.format(UPDATE_MESSAGE, CITY)));
        String requestBodyNoName = """
                {
                "info" : "capital and largest city of Germany"
                }
                """;
        this.mockMvc.perform(patch("/city/1")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBodyNoName))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(content().json("{\"errors\":[\"The 'name' field is required\"],\"type\":\"The transmitted data did not pass verification\"}"));
        String requestBodyInvalidField = """
                {
                "names" : "Istanbul",
                "info" : "former Constantinople, ancient Byzantium, the largest city and the main seaport of Turkey"
                }
                """;
        this.mockMvc.perform(patch("/city/1")
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
        this.mockMvc.perform(patch("/city/2")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBodyUnnecessaryField))
                    .andDo(print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("message").value(HTTP_NOT_READABLE_EXCEPTION_MESSAGE))
                    .andExpect(jsonPath("type").value("HttpMessageNotReadableException"));
        this.mockMvc.perform(patch("/cities/1"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
    }
}