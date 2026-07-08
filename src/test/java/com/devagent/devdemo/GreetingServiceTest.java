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
}
