# Playwright MCP Server

This directory contains the Model Context Protocol (MCP) server implementation for integrating Playwright testing with LLMs.

## Overview

The MCP server provides tools for:
- Running Playwright tests
- Generating new tests
- Analyzing test failures
- Retrieving test reports

## Structure

- `package.json` - Node.js project configuration
- `index.js` - Main MCP server implementation
- `README.md` - This file

## Installation

```bash
npm install
```

## Running the Server

```bash
npm start
```

For development with auto-reload:

```bash
npm run dev
```

## Tools Provided

### run_test
Executes a specific Playwright test or test suite.

### generate_test
Generates a new Playwright test based on requirements.

### analyze_failure
Analyzes test failures and suggests fixes.

### get_test_report
Retrieves test reports in various formats.

## Integration with Java/Maven

This MCP server communicates with the Java/Maven Playwright project to:
- Execute tests
- Generate test reports
- Provide test metrics to LLMs

## Configuration

The server connects to the parent Maven project and provides a bridge between:
- LLMs (Claude, etc.)
- Playwright test execution
- Test analysis and reporting

