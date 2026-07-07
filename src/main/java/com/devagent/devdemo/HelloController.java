package com.devagent.devdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
   @GetMapping("/hello2")
    public String hello2() {
        return "Hello from DevAgent Demo!";
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "Goodbye from DevAgent Demo!";
    }
}