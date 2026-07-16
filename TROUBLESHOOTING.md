# Troubleshooting Guide

## Common Issues and Solutions

### 🔴 Issue: "Claude doesn't see my tools"

**Symptoms:**
- Claude doesn't respond to "Do you have Playwright tools?"
- MCP server not connecting

**Solutions:**

1. **Restart Claude Completely**
   - Close Claude Desktop entirely
   - Wait 5 seconds
   - Reopen Claude Desktop
   - Wait for it to fully load

2. **Verify Configuration File**
   - File location: `%APPDATA%\Claude\claude_desktop_config.json`
   - Check the path is correct (absolute path, with `\\`)
   - Example:
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
   - Make sure there are no trailing commas
   - Validate JSON syntax on https://jsonlint.com/

3. **Verify npm install was run**
   ```bash
   cd C:\Users\Lakshmanan\IdeaProjects\SP-MCP1\playwright-mcp
   npm install
   ```

4. **Check Node.js is installed**
   ```bash
   node --version
   ```
   - Should be 16.0.0 or higher
   - Download from https://nodejs.org if not installed

---

### 🔴 Issue: "npm install fails"

**Symptoms:**
- `npm: command not found`
- `ERR! code ENOTFOUND`

**Solutions:**

1. **Install Node.js**
   - Download from https://nodejs.org/
   - Run the installer
   - Restart your terminal/PowerShell
   - Verify: `node --version`

2. **Check npm installation**
   ```bash
   npm --version
   ```

3. **Clear npm cache**
   ```bash
   npm cache clean --force
   npm install
   ```

4. **Use different network**
   - If behind corporate proxy, configure npm:
     ```bash
     npm config set registry https://registry.npmjs.org/
     ```

---

### 🔴 Issue: "Maven tests won't run"

**Symptoms:**
- `mvn: command not found`
- Tests fail with Playwright browser not found
- `No tests were executed`

**Solutions:**

1. **Install Maven** (if not already installed)
   ```bash
   # Check if installed
   mvn --version
   ```
   - If not found, download from https://maven.apache.org/
   - Add Maven to PATH

2. **Install Playwright Browsers**
   ```bash
   mvn install -P install-playwright-browsers
   ```

3. **Check Java Version**
   ```bash
   java --version
   ```
   - Must be 21 or higher
   - Download from https://www.oracle.com/java/technologies/

4. **Clean and rebuild**
   ```bash
   mvn clean install -DskipTests
   mvn install -P install-playwright-browsers
   ```

---

### 🔴 Issue: "Tests timeout or fail with 'Element not found'"

**Symptoms:**
- `TimeoutError: waiting for element`
- `Error: element not found`
- Tests pass locally but fail in CI

**Solutions:**

1. **Add explicit waits**
   ```java
   page.waitForSelector("input[type='submit']", new Page.WaitForSelectorOptions().setTimeout(10000));
   ```

2. **Use proper locators**
   ```java
   // Good
   page.click("text=Login");
   
   // Also good
   page.click("button:has-text('Login')");
   
   // Avoid brittle XPath
   // page.click("/html/body/div[1]/button");
   ```

3. **Increase timeout for slow environments**
   ```bash
   mvn test -DargLine="-Dplaywright.timeout=30000"
   ```

4. **Check if element is visible**
   ```java
   Locator button = page.locator("button:has-text('Submit')");
   assertTrue(button.isVisible(), "Button should be visible");
   button.click();
   ```

---

### 🔴 Issue: "Screenshots not showing in Allure reports"

**Symptoms:**
- Tests fail but screenshots don't appear in Allure
- FailureScreenshotExtension not being called

**Solutions:**

1. **Verify extension is registered**
   - Check `junit-platform.properties` exists at `src/test/resources/`
   - It should contain:
     ```properties
     junit.jupiter.extensions.autodetection.enabled=true
     ```

2. **Check extension implementation**
   - Make sure `FailureScreenshotExtension` implements `AfterTestExecutionCallback`
   - Check for syntax errors

3. **Generate Allure report**
   ```bash
   mvn allure:report
   mvn allure:serve
   ```

4. **Check Allure agent is registered**
   ```bash
   mvn test -Xvs
   ```
   - Look for Allure listener in output

---

### 🔴 Issue: "Port already in use" (for Allure serve)

**Symptoms:**
- Error: `Address already in use: bind`
- When running `mvn allure:serve`

**Solutions:**

1. **Kill existing process**
   ```bash
   # PowerShell
   Get-Process | Where-Object {$_.Port -eq 4040} | Stop-Process -Force
   ```

2. **Use different port**
   ```bash
   mvn allure:serve -Dport=4050
   ```

3. **Check what's using the port**
   ```bash
   netstat -ano | findstr :4040
   taskkill /PID <PID> /F
   ```

---

### 🔴 Issue: "Git conflicts or file issues"

**Symptoms:**
- `.gitignore` conflicts
- Cannot see new files in IDE

**Solutions:**

1. **Refresh IDE**
   - IntelliJ IDEA → File → Invalidate Caches / Restart
   - VS Code → Reload Window (Ctrl+Shift+P → Reload)

2. **Update .gitignore** (optional)
   ```bash
   echo "node_modules/" >> .gitignore
   echo "playwright-mcp/node_modules/" >> .gitignore
   ```

3. **Commit changes**
   ```bash
   git add -A
   git commit -m "Add Playwright MCP integration"
   ```

---

### 🔴 Issue: "Allure report shows no tests"

**Symptoms:**
- Report is empty
- `0 of 0 passed`

**Solutions:**

1. **Verify tests actually run**
   ```bash
   mvn test -Dtest=ExampleTest
   ```

2. **Check test directory structure**
   ```
   src/test/java/ExampleTest.java
   src/test/resources/junit-platform.properties
   ```

3. **Verify test class structure**
   ```java
   public class ExampleTest {
       @Test
       public void testSomething() {
           // ...
       }
   }
   ```

4. **Regenerate report**
   ```bash
   rm -rf target/allure-results
   mvn clean test
   mvn allure:report
   ```

---

### 🟡 Issue: "Slow test execution"

**Symptoms:**
- Tests take too long
- Timeout during CI/CD

**Solutions:**

1. **Run tests in parallel**
   ```bash
   mvn test -DargLine="-DparallelThreads=4"
   ```

2. **Reduce headless overhead**
   ```java
   browser.launch(new BrowserType.LaunchOptions().setHeadless(true));
   ```

3. **Disable screenshots for passing tests**
   - Only capture on failures (already configured)

4. **Run subset of tests first**
   ```bash
   mvn test -Dtest=OrangeHRM/LoginTest
   ```

---

### 🟡 Issue: "MCP server connection timeout"

**Symptoms:**
- Claude tries to connect but times out
- Logs show connection errors

**Solutions:**

1. **Verify Node.js server can start**
   ```bash
   cd playwright-mcp
   node index.js
   ```
   - You should see: `Playwright MCP server running on stdio`
   - Press Ctrl+C to stop

2. **Check for syntax errors**
   ```bash
   node -c index.js
   ```
   - If no output, syntax is fine

3. **Install missing dependencies**
   ```bash
   cd playwright-mcp
   npm install
   cd ..
   ```

---

## 📞 Getting Help

1. **Check documentation files**
   - [SETUP_INSTRUCTIONS.md](./SETUP_INSTRUCTIONS.md)
   - [mcp_instructions.md](./mcp_instructions.md)
   - [README.md](./README.md)

2. **Ask Claude directly**
   - "I'm getting a timeout error in my test, how can I fix it?"
   - Claude can help debug Playwright issues

3. **Check error messages carefully**
   - Run with `-X` flag for verbose output:
     ```bash
     mvn test -X
     ```

4. **Search online**
   - Error message + "Playwright"
   - Error message + "Maven"

## ✅ Verification Checklist

Before assuming something is broken, verify:

- [ ] Java 21+ installed: `java --version`
- [ ] Maven installed: `mvn --version`
- [ ] Node.js installed: `node --version`
- [ ] npm install run: Files exist in `playwright-mcp/node_modules/`
- [ ] Claude completely restarted (not just minimized)
- [ ] Configuration file has correct path (absolute path with `\\`)
- [ ] No JSON syntax errors in config file
- [ ] Playwright browsers installed: `mvn install -P install-playwright-browsers`

---

**Still having issues?** Ask Claude: "I'm having trouble with [specific error message]"

