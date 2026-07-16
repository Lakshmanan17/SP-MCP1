package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example Playwright Test
 *
 * This is a template test class showing how to structure Playwright tests
 * in this project. You can use this as a starting point for creating new tests.
 */
@DisplayName("Example Playwright Tests")
public class ExampleTest {

    private Browser browser;
    private Page page;

    @BeforeEach
    public void setUp() {
        browser = Playwright.create().chromium().launch();
        page = browser.newPage();
    }

    @Test
    @DisplayName("Should navigate to page and verify title")
    public void testPageNavigation() {
        // Arrange
        String url = "https://example.com";

        // Act
        page.navigate(url);
        String title = page.title();

        // Assert
        assertTrue(title.contains("Example"), "Page title should contain 'Example'");
    }

    @Test
    @DisplayName("Should fill and submit form")
    public void testFormSubmission() {
        // Arrange
        page.navigate("https://example.com");

        // Act
        page.fill("input[name='email']", "test@example.com");
        page.click("button[type='submit']");
        page.waitForTimeout(1000);

        // Assert
        String url = page.url();
        assertNotEquals("https://example.com", url, "Page should redirect after form submission");
    }

    @Test
    @DisplayName("Should handle multiple page interactions")
    public void testMultipleInteractions() {
        // Arrange
        page.navigate("https://example.com");

        // Act
        page.click("button.menu");
        page.click("a:has-text('About')");
        String currentUrl = page.url();

        // Assert
        assertFalse(currentUrl.contains("example.com/main"), "Should navigate away from main page");
    }

    // Cleanup
    public void tearDown() {
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
    }
}

