package com.devagent.devdemo;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello2")
    public String hello2() {
        return greetingService.demoGreeting(null);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name) {
        return greetingService.demoGreeting(name);
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "Goodbye from DevAgent Demo!";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/hola")
    public String hola() {
        return greetingService.holaGreeting();
    }
}