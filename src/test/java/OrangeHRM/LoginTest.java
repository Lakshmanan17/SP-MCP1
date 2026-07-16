package OrangeHRM;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OrangeHRM Login Page Tests")
public class LoginTest {

    private Playwright pw;
    private Browser browser;
    private Page page;

    @BeforeEach
    void setUp() {
        // Use system properties to control headless and channel; default to headless=true and channel=chrome
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        String channel = System.getProperty("browserChannel", "chrome");

        pw = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(headless);
        if (channel != null && !channel.isEmpty()) {
            // Try to launch the system Chrome/Chromium if available
            options.setChannel(channel);
        }
        browser = pw.chromium().launch(options);
        page = browser.newPage();
    }

    @Test
    @DisplayName("Should load OrangeHRM login page and find username input")
    void shouldLoadLoginPage() {
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        page.navigate(url);

        // Wait up to 15s for the username input to appear
        page.waitForSelector("input[name='username']", new Page.WaitForSelectorOptions().setTimeout(15000));
        Locator username = page.locator("input[name='username']");
        assertTrue(username.isVisible(), "Username input should be visible on the login page");
    }

    @AfterEach
    void tearDown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (pw != null) pw.close();
    }
}

