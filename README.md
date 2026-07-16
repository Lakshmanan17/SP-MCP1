# Project README

## SP-MCP1: Playwright + MCP + LLM Integration

Welcome to SP-MCP1, a complete **Playwright Java testing framework** integrated with **Model Context Protocol (MCP)** for seamless **LLM-assisted test automation**.

## 🎯 What You Can Do

With this project, you can:

✨ **Write tests with AI assistance** - Ask Claude to generate test code  
🚀 **Run tests from chat** - Execute Playwright tests directly through Claude  
🔍 **Debug with AI** - Get intelligent debugging suggestions for failing tests  
📊 **Generate reports** - Produce and analyze Allure test reports  
💡 **Improve code** - Have Claude review and optimize your test code  

## 🏗️ Project Structure

```
SP-MCP1/
├── playwright-mcp/              # MCP server for LLM integration
├── src/
│   ├── main/java/              # Application source code
│   └── test/java/
│       ├── OrangeHRM/          # Test classes
│       ├── OrangeHRM-Utils/    # Test utilities & extensions
│       └── ExampleTest.java    # Template test file
├── pom.xml                      # Maven configuration
├── SETUP_INSTRUCTIONS.md        # Quick start guide
├── mcp_instructions.md          # MCP documentation
├── mcp_config.json             # MCP server config
└── claude_setup.md             # Claude integration guide
```

## 🚀 Quick Start

### 1. Install Dependencies

```bash
# Install Java dependencies
mvn clean install

# Install Node dependencies for MCP
cd playwright-mcp
npm install
cd ..
```

### 2. Install Playwright Browsers

```bash
mvn install -P install-playwright-browsers
```

### 3. Setup Claude Integration

Follow the instructions in **SETUP_INSTRUCTIONS.md** to configure Claude Desktop with your MCP server.

### 4. Run Tests

```bash
# All tests
mvn test

# Specific test
mvn test -Dtest=TestClassName

# Through Claude
# (Ask Claude: "Run all tests")
```

## 📚 Key Features

### Playwright Testing
- Modern browser automation with Playwright Java
- Support for Chromium, Firefox, and WebKit
- Powerful locators and interactions
- Network monitoring and API mocking

### JUnit 5 Framework
- Parameterized tests
- Fixtures and extensions
- Conditional test execution
- Test lifecycle management

### Automatic Failure Reporting
- **FailureScreenshotExtension** automatically captures screenshots on test failures
- Integrates with Allure for detailed reporting
- Full page screenshots for debugging

### Allure Reporting
- Beautiful HTML test reports
- Test history and trends
- Failure analysis with screenshots
- Customizable report templates

### MCP Integration
- Run tests from Claude and other LLMs
- Generate new tests with natural language
- Analyze failures with AI assistance
- Get test recommendations

## 🛠️ Commands Reference

```bash
# Run all tests
mvn test

# Run test with pattern matching
mvn test -Dtest=OrangeHRM.*

# Generate Allure report
mvn allure:report

# View live Allure report
mvn allure:serve

# Run specific test
mvn test -Dtest=LoginTest

# Run with detailed output
mvn test -X

# Skip tests during build
mvn clean install -DskipTests
```

## 📖 Documentation

- **[SETUP_INSTRUCTIONS.md](./SETUP_INSTRUCTIONS.md)** - Complete setup guide for Claude integration
- **[claude_setup.md](./claude_setup.md)** - Step-by-step Claude Desktop configuration
- **[mcp_instructions.md](./mcp_instructions.md)** - Project overview and MCP details
- **[mcp_config.json](./mcp_config.json)** - MCP server configuration

## 🔧 Project Dependencies

### Java/Maven
- **Java**: 21+
- **Maven**: 3.6+
- **Playwright**: 1.60.0
- **JUnit 5**: Latest
- **Allure**: 2.35.3
- **Kotlin**: 2.3.10

### Node.js (for MCP)
- **Node.js**: 16+
- **MCP SDK**: ^0.5.0

## 📝 Creating Tests

### Basic Test Template

```java
@DisplayName("Example Test")
public class MyTest {
    private Browser browser;
    private Page page;
    
    @BeforeEach
    public void setUp() {
        browser = Playwright.create().chromium().launch();
        page = browser.newPage();
    }
    
    @Test
    public void testExample() {
        page.navigate("https://example.com");
        // Add your test logic here
    }
    
    public void tearDown() {
        page.close();
        browser.close();
    }
}
```

### With OrangeHRM Example

Tests in the `OrangeHRM` package follow the pattern of testing OrangeHRM application.

## 🤖 Using with Claude

### Example Interactions

**Generate a test:**
```
Create a Playwright test for the OrangeHRM login page that:
1. Enters username and password
2. Clicks login button
3. Verifies user is logged in
```

**Run tests:**
```
Run all tests in the OrangeHRM package
```

**Analyze failure:**
```
My test failed with "TimeoutError: waiting for element". How can I make it more robust?
```

**Get report:**
```
Generate the Allure test report and show me the summary
```

## 🐛 Troubleshooting

### Tests not running
- Check Java version: `java --version` (needs 21+)
- Check Maven installation: `mvn --version`
- Install Playwright browsers: `mvn install -P install-playwright-browsers`

### MCP not connecting to Claude
- Restart Claude Desktop completely
- Verify configuration in `%APPDATA%\Claude\claude_desktop_config.json`
- Ensure `npm install` was run in the `playwright-mcp` directory

### Screenshots not appearing in reports
- Check `FailureScreenshotExtension` is registered in JUnit configuration
- Verify `junit-platform.properties` is in the test resources directory

## 🔗 External Resources

- [Playwright Java Documentation](https://playwright.dev/java/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Allure Framework](https://docs.qameta.io/allure/)
- [Model Context Protocol](https://modelcontextprotocol.io/)

## 📞 Support

For issues or questions:
1. Check the relevant documentation file above
2. Run `mvn test -X` for debug output
3. Ask Claude directly for debugging help
4. Check project issues on the repository

## 📄 License

This project follows the terms specified in the LICENSE file.

---

**Ready to get started?** Follow the [SETUP_INSTRUCTIONS.md](./SETUP_INSTRUCTIONS.md) to configure your MCP integration with Claude! 🚀

