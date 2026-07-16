# Playwright MCP Instructions

This project is configured as a Model Context Protocol (MCP) server for Playwright testing with LLM integration.

## Project Overview

This is a **Playwright JUnit5** testing project integrated with MCP for AI-assisted test automation and analysis.

## Project Structure

```
SP-MCP1/
├── playwright-mcp/          # MCP server configuration
├── src/
│   ├── main/               # Main application code
│   │   ├── java/
│   │   │   └── org/example/
│   │   └── resources/
│   └── test/               # Test code
│       ├── java/
│       │   ├── OrangeHRM/           # Test classes
│       │   └── OrangeHRM-Utils/     # Test utilities
│       └── resources/
├── pom.xml                 # Maven configuration
└── mcp_instructions.md     # This file
```

## Technologies

- **Framework**: Playwright Java
- **Test Runner**: JUnit 5
- **Reporting**: Allure Reports
- **Logging**: SLF4J
- **Build**: Maven
- **Java Version**: 21

## Key Components

### Test Framework
- Playwright for browser automation
- JUnit 5 for test execution
- Allure for test reporting

### Custom Extensions
- **FailureScreenshotExtension**: Automatically captures screenshots on test failures and attaches them to Allure reports

### Test Structure
- **OrangeHRM package**: Contains actual test implementations
- **OrangeHRM-Utils package**: Contains utility classes and extensions

## Running Tests

### Run all tests
```bash
mvn test
```

### Run specific test class
```bash
mvn test -Dtest=TestClassName
```

### Generate Allure report
```bash
mvn allure:report
```

### View Allure report
```bash
mvn allure:serve
```

## MCP Configuration

This project is configured to work with:
- Claude and other LLMs via Model Context Protocol
- Automated test generation
- Test analysis and optimization
- Code review and improvements

## Features

✅ Playwright browser automation  
✅ JUnit 5 test framework  
✅ Automatic failure screenshots  
✅ Allure test reporting  
✅ Maven build automation  
✅ Kotlin support  
✅ MCP integration for LLM assistance  

## Getting Started

1. **Install dependencies**:
   ```bash
   mvn clean install
   ```

2. **Install Playwright browsers**:
   ```bash
   mvn install -P install-playwright-browsers
   ```

3. **Run tests**:
   ```bash
   mvn test
   ```

4. **View reports**:
   ```bash
   mvn allure:serve
   ```

## Configuration Files

- `pom.xml`: Maven project configuration
- `junit-platform.properties`: JUnit 5 configuration

## Environment Setup

Ensure you have:
- JDK 21+ installed
- Maven 3.6+ installed
- Playwright browsers installed (automatic via maven plugin)

## LLM Integration

This project is configured for seamless integration with LLMs through MCP:
- Ask Claude to generate tests
- Request code reviews
- Get optimization suggestions
- Debug test failures with AI assistance

## Support

For issues or questions, refer to:
- [Playwright Documentation](https://playwright.dev/java/)
- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [Allure Documentation](https://docs.qameta.io/allure/)

