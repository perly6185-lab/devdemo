package com.devagent.devdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
@Import(GreetingService.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloShouldReturnMessage() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from DevAgent Demo!"));
    }

    @Test
    void helloWithNameShouldReturnPersonalizedMessage() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Ada"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Ada! (from DevAgent Demo)"));
    }

    @Test
    void healthShouldReturnUpStatus() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"UP\"}"));
    }

    @Test
    void goodbyeShouldReturnFarewell() throws Exception {
        mockMvc.perform(get("/goodbye"))
                .andExpect(status().isOk())
                .andExpect(content().string("Goodbye from DevAgent Demo!"));
    }

    @Test
    void pingShouldReturnPong() throws Exception {
        mockMvc.perform(get("/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string("pong"));
    }

    @Test
    void statusShouldReturnOkJson() throws Exception {
        mockMvc.perform(get("/status"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void healthzShouldReturnHealthyJson() throws Exception {
        mockMvc.perform(get("/healthz"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.healthy").value(true));
    }

    @Test
    void holaShouldReturnSpanishGreeting() throws Exception {
        mockMvc.perform(get("/hola"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola"));
    }
}