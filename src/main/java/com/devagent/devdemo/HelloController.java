package com.devagent.devdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
   @GetMapping("/hello2")
    public String hello2() {
        return "Hello from DevAgent Demo!";
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name) {
        if (name != null) {
            return "Hello, " + name + "! (from DevAgent Demo)";
        }
        return "Hello from DevAgent Demo!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "Goodbye from DevAgent Demo!";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/ready")
    public String ready() {
        return "ready";
    }
}