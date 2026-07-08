package com.devagent.devdemo;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    /**
     * Builds a personalized English greeting from the given name.
     *
     * @param name the name to greet; when null or blank a sensible default is used
     * @return a personalized greeting (e.g. "Hello, Ada!") or a default greeting
     */
    public String greet(String name) {
        if (name == null || name.isBlank()) {
            return "Hello!";
        }
        return "Hello, " + name.trim() + "!";
    }
}
