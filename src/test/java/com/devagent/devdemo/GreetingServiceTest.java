package com.devagent.devdemo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingServiceTest {

    private final GreetingService greetingService = new GreetingService();

    @Test
    void greetWithNameReturnsPersonalizedGreeting() {
        assertEquals("Hello, Ada!", greetingService.greet("Ada"));
    }

    @Test
    void greetWithNullNameReturnsDefaultGreeting() {
        assertEquals("Hello!", greetingService.greet(null));
    }

    @Test
    void greetWithBlankNameReturnsDefaultGreeting() {
        assertEquals("Hello!", greetingService.greet("   "));
    }

    @Test
    void greetWithEnglishLangReturnsEnglishGreeting() {
        assertEquals("Hello, Ada!", greetingService.greet("Ada", "en"));
    }

    @Test
    void greetWithEnglishLangAndNoNameReturnsEnglishDefault() {
        assertEquals("Hello!", greetingService.greet(null, "en"));
    }

    @Test
    void greetWithSpanishLangReturnsSpanishGreeting() {
        assertEquals("Hola, Ada!", greetingService.greet("Ada", "es"));
    }

    @Test
    void greetWithSpanishLangAndNoNameReturnsSpanishDefault() {
        assertEquals("¡Hola!", greetingService.greet(null, "es"));
    }

    @Test
    void greetWithUnsupportedLangFallsBackToEnglish() {
        assertEquals("Hello, Ada!", greetingService.greet("Ada", "fr"));
    }

    @Test
    void greetWithUnsupportedLangAndNoNameFallsBackToEnglishDefault() {
        assertEquals("Hello!", greetingService.greet(null, "fr"));
    }

    @Test
    void greetWithBlankNameAndSpanishLangReturnsSpanishDefault() {
        assertEquals("¡Hola!", greetingService.greet("   ", "es"));
    }
}
