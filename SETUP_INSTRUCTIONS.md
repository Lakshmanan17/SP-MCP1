# Complete MCP Setup Instructions

## What is MCP?

**Model Context Protocol (MCP)** allows Large Language Models (LLMs) like Claude to interact with external tools and systems. By setting up MCP with your Playwright project, you can:

- **Ask Claude to write tests** for you
- **Run tests directly** through Claude
- **Get AI-powered debugging** for test failures
- **Generate test reports** with AI analysis
- **Review test code** with Claude's assistance

## Setup Steps

### 1. Install Node.js Dependencies

Open a terminal and navigate to your project:

```bash
cd C:\Users\Lakshmanan\IdeaProjects\SP-MCP1\playwright-mcp
npm install
```

This installs the MCP SDK needed for LLM integration.

### 2. Configure Claude Desktop

#### For Windows:

1. Find your Claude config file at: `%APPDATA%\Claude\claude_desktop_config.json`
2. Open it with a text editor (Notepad, VS Code, etc.)
3. Update the configuration to include your MCP server:

```json
{
  "mcpServers": {
    "playwright": {
      "command": "node",
      "args": ["C:\\Users\\Lakshmanan\\IdeaProjects\\SP-MCP1\\playwright-mcp\\index.js"]
    }
  }
}
```

4. Save the file
5. **Completely restart Claude Desktop** (close and reopen)

### 3. Verify the Setup

In Claude, ask:
```
What tools do you have available for Playwright testing?
```

If set up correctly, Claude should list:
- run_test
- generate_test
- analyze_failure
- get_test_report

### 4. Start Using Claude with Your Tests

#### Example 1: Generate a Test
```
Create a Playwright test that:
1. Navigates to the OrangeHRM login page
2. Enters valid credentials
3. Verifies successful login
4. Takes a screenshot
```

Claude will generate test code you can copy into your project.

#### Example 2: Run Existing Tests
```
Run all tests in the OrangeHRM package
```

Claude will execute: `mvn test -Dtest=OrangeHRM.*`

#### Example 3: Analyze Failures
```
I have a test that fails intermittently. The error is "Element not found". 
Can you suggest improvements to make it more reliable?
```

Claude can analyze your test code and suggest waits, retry logic, etc.

## Project Structure After Setup

```
SP-MCP1/
├── playwright-mcp/
│   ├── node_modules/              ← Installed by npm install
│   ├── package.json               ← Node dependencies
│   ├── package-lock.json          ← Locked versions
│   ├── index.js                   ← MCP server code
│   └── README.md
├── src/
│   ├── main/java/org/example/
│   └── test/java/
│       ├── OrangeHRM/             ← Your tests
│       ├── OrangeHRM-Utils/       ← Test utilities (FailureScreenshotExtension)
│       └── ExampleTest.java       ← Template test
├── .idea/
├── .git/
├── pom.xml                        ← Maven configuration
├── mcp_instructions.md            ← Project documentation
├── mcp_config.json               ← MCP tool configuration
└── claude_setup.md               ← This setup guide
```

## Useful Maven Commands to Know

```bash
# Install project and Playwright browsers
mvn clean install -P install-playwright-browsers

# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=MyTestClass

# Run tests matching a pattern
mvn test -Dtest=OrangeHRM*

# Generate Allure report
mvn allure:report

# View Allure report in browser
mvn allure:serve
```

## How MCP Works in Your Project

```
┌─────────────┐
│   Claude    │  "Run my Playwright tests"
└──────┬──────┘
       │
       ↓ (MCP Protocol)
┌──────────────────────────────┐
│ Playwright MCP Server        │
│ (playwright-mcp/index.js)    │
└──────┬───────────────────────┘
       │
       ↓ (Executes Maven command)
┌──────────────────────────────┐
│ Maven (pom.xml)              │
│ ├─ JUnit 5                   │
│ ├─ Playwright                │
│ └─ Allure Reports            │
└──────┬───────────────────────┘
       │
       ↓ (Returns results)
┌──────────────────────────────┐
│ Test Results Back to Claude  │
│ "5 tests passed, 1 failed"   │
└──────────────────────────────┘
```

## Common Issues & Solutions

### Problem: "Claude doesn't see the tools"
**Solution**: 
- Make sure you completely closed and reopened Claude
- Check the config file path is correct
- Verify no JSON syntax errors in claude_desktop_config.json

### Problem: "Node command not found"
**Solution**:
- Install Node.js from https://nodejs.org/
- Add Node to your PATH environment variable
- Restart your terminal

### Problem: "Tests won't run through Claude"
**Solution**:
- Ensure Maven is installed and in PATH: `mvn --version`
- Install Playwright browsers: `mvn install -P install-playwright-browsers`
- Check Java version is 21+: `java --version`

### Problem: "MCP server connection error"
**Solution**:
- Check that `npm install` was run in the playwright-mcp directory
- Verify the path in claude_desktop_config.json is the absolute path to index.js
- Look at Claude's error messages for more details

## Next Steps

1. ✅ Run `npm install` in the playwright-mcp folder
2. ✅ Configure Claude Desktop
3. ✅ Test the setup with a simple command in Claude
4. ✅ Start creating and managing tests with Claude's help!

## Getting Help

- **For Playwright issues**: Visit https://playwright.dev/java/
- **For MCP questions**: Check https://modelcontextprotocol.io/
- **For Claude help**: Ask Claude directly! It can help debug its own integration.

---

**You're all set!** Your project is now a Playwright MCP system ready for LLM-assisted test automation. 🚀

