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
     * <p>Fallback behavior at the boundaries:
     * <ul>
     *   <li>A null, blank, or whitespace-only {@code name} is treated the same as no
     *       name: the generic greeting for the selected language is returned.</li>
     *   <li>A null, blank, or unsupported/unrecognized {@code lang} (anything other
     *       than "es", case-insensitive) falls back to the default language, English.</li>
     * </ul>
     *
     * @param name the name to greet; when null or blank a sensible default is used
     * @param lang the language code ('en' or 'es'); when null, blank, or unsupported English is used
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
