package com.devagent.devdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetController.class)
@Import(GreetingService.class)
class GreetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void greetWithNameShouldReturnPersonalizedGreeting() throws Exception {
        mockMvc.perform(get("/greet").param("name", "Ada"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Ada!"));
    }

    @Test
    void greetWithoutNameShouldReturnDefaultGreeting() throws Exception {
        mockMvc.perform(get("/greet"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello!"));
    }
}
