package by.tms.rest.template;


import static org.assertj.core.api.Assertions.assertThat;

import by.tms.rest.template.controller.CityController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestTemplateApplicationTests {

    @Autowired
    private CityController cityController;

    @Test
    void contextLoads() {
        assertThat(cityController).isNotNull();
    }

}
