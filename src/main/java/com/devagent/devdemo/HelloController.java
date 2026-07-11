package com.devagent.devdemo;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final GreetingService greetingService;

    private final Instant startTime = Instant.now();

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

    @GetMapping("/status")
    public Map<String, String> status() {
        return Map.of("status", "ok");
    }

    @GetMapping("/uptime")
    public Map<String, Long> uptime() {
        return Map.of("uptimeSeconds", Duration.between(startTime, Instant.now()).getSeconds());
    }

    @GetMapping("/hola")
    public String hola() {
        return greetingService.holaGreeting();
    }
}