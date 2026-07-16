# Playwright MCP Setup Guide

## Quick Start

This project is now configured as a Playwright MCP (Model Context Protocol) server for LLM integration.

### Step 1: Install MCP Server Dependencies

```bash
cd playwright-mcp
npm install
```

### Step 2: Configure Your LLM Client

#### For Claude Desktop

1. Locate your Claude Desktop configuration file:
   - **macOS**: `~/Library/Application Support/Claude/claude_desktop_config.json`
   - **Windows**: `%APPDATA%\Claude\claude_desktop_config.json`
   - **Linux**: `~/.config/Claude/claude_desktop_config.json`

2. Add the MCP server configuration:

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

3. Restart Claude Desktop

### Step 3: Verify Setup

In Claude, ask:
```
Can you run my Playwright tests?
```

Claude should be able to see the available Playwright tools.

### Step 4: Use with Claude

#### Generate a new test
```
Create a Playwright test that navigates to https://example.com and verifies the page title
```

#### Run tests
```
Run all tests in the OrangeHRM test suite
```

#### Analyze failures
```
Analyze why my login test failed and suggest fixes
```

#### Get reports
```
Generate and show me the test report
```

## Project Commands

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=TestClassName

# Generate Allure report
mvn allure:report

# View Allure report
mvn allure:serve

# Install Playwright browsers
mvn install -P install-playwright-browsers
```

## Project Structure

```
SP-MCP1/
├── playwright-mcp/              # MCP server (communicates with LLMs)
│   ├── index.js                # Main MCP server
│   ├── package.json            # Node dependencies
│   └── README.md               # Server documentation
├── src/
│   ├── main/java/             # Application code
│   └── test/java/             # Playwright tests
├── pom.xml                      # Maven configuration
├── mcp_config.json             # MCP configuration
├── mcp_instructions.md         # Project instructions
└── claude_setup.md             # This file
```

## Features

✅ **Test Automation**: Run Playwright tests from Claude  
✅ **AI Test Generation**: Generate new tests with natural language  
✅ **Failure Analysis**: Get AI-powered debugging suggestions  
✅ **Test Reports**: Access Allure reports through MCP  
✅ **Code Assistance**: Ask Claude to review and improve your tests  

## Troubleshooting

### MCP Server not connecting
- Ensure Node.js is installed: `node --version`
- Verify the path in your LLM config is correct
- Check that `npm install` was run in the `playwright-mcp` directory

### Tests not running
- Ensure Maven is installed: `mvn --version`
- Install Playwright browsers: `mvn install -P install-playwright-browsers`
- Check Java version is 21+: `java --version`

### Claude can't see the tools
- Restart Claude Desktop completely
- Verify the MCP server configuration file path
- Check for JSON syntax errors in the config

## Next Steps

1. ✅ Created MCP server structure
2. ✅ Added configuration files
3. 🔲 Run `npm install` in `playwright-mcp` directory
4. 🔲 Configure your LLM client
5. 🔲 Start using Claude to manage your tests!

## Support Resources

- [Playwright Documentation](https://playwright.dev/java/)
- [Model Context Protocol](https://modelcontextprotocol.io/)
- [Claude Documentation](https://claude.ai/help)

