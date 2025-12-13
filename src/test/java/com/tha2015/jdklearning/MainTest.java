package com.tha2015.jdklearning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Main class tests")
class MainTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        // Restore original System.out
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("main() should print greeting messages")
    void testMainPrintsGreeting() {
        // When
        Main.main(new String[]{});

        // Then
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Hello, JDK Learning!"),
                   "Output should contain greeting");
        assertTrue(output.contains("Hi"),
                   "Output should contain Hi");
    }

    @Test
    @DisplayName("main() should not throw exceptions")
    void testMainDoesNotThrow() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    @Test
    @DisplayName("Main class should have public constructor")
    void testMainClassHasPublicConstructor() {
        assertDoesNotThrow(() -> new Main());
    }
}
