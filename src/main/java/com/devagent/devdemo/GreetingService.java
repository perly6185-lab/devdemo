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
        return greet(name, null);
    }

    /**
     * Builds a personalized greeting from the given name in the requested language.
     *
     * @param name the name to greet; when null or blank a sensible default is used
     * @param lang the language code ('en' or 'es'); when null or blank English is used
     * @return a personalized greeting (e.g. "Hello, Ada!" or "Hola, Ada!") or a default greeting
     */
    public String greet(String name, String lang) {
        boolean spanish = lang != null && lang.trim().equalsIgnoreCase("es");
        if (name == null || name.isBlank()) {
            return spanish ? "¡Hola!" : "Hello!";
        }
        String trimmedName = name.trim();
        return spanish ? "Hola, " + trimmedName + "!" : "Hello, " + trimmedName + "!";
    }
}
