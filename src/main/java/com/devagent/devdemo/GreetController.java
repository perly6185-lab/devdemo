package com.devagent.devdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    private final GreetingService greetingService;

    public GreetController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(required = false) String name,
                        @RequestParam(required = false) String lang) {
        return greetingService.greet(name, lang);
    }
}
