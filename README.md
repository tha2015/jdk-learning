# JDK Learning

Learning Java JDK internals and core concepts with browser-based execution using TeaVM.

## Features

- ☕ Java 21 development
- 🧪 JUnit 5 testing
- 🌐 Runs in browser via TeaVM JavaScript compilation
- 🚀 Automated GitHub Pages deployment
- 🐳 Docker-based development (no local JDK required)

## Live Demo

Visit the live demo: https://tha2015.github.io/jdk-learning/

The Java code is compiled to JavaScript and runs directly in your browser!

## Quick Start

### Prerequisites

- Docker (for development without local JDK)
- OR Java 21 (if you have it installed locally)

### Commands

#### Development (using Maven Wrapper)

```bash
# Run the application
./mvnw compile exec:java

# Or use make
make run

# Run tests
./mvnw test
# Or: make test

# Build everything including JavaScript
./mvnw clean package
# Or: make package

# Preview GitHub Pages locally
make pages
```

#### Using Docker (Alternative)

```bash
# Run application in Docker
make docker-run

# Run tests in Docker
make docker-test

# Build package in Docker
make docker-package
```

#### Clean Build

```bash
./mvnw clean
# Or: make clean
```

## Project Structure

```
jdk-learning/
├── src/
│   ├── main/
│   │   ├── java/com/tha2015/jdklearning/
│   │   │   └── Main.java          # Main application
│   │   └── webapp/
│   │       └── index.html         # HTML template for browser
│   └── test/
│       └── java/com/tha2015/jdklearning/
│           └── MainTest.java      # JUnit tests
├── target/
│   └── generated/js/              # TeaVM JavaScript output
│       ├── index.html             # Generated page
│       └── classes.js             # Compiled JavaScript
├── pom.xml                        # Maven configuration
├── Makefile                       # Build shortcuts
└── README.md                      # This file
```

## How It Works

1. **Development**: Write Java code using Java 21 features
2. **Testing**: Test with JUnit 5 in JVM environment
3. **Compilation**: Maven compiles Java to bytecode
4. **Transformation**: TeaVM transforms bytecode to JavaScript
5. **Deployment**: GitHub Actions deploys to GitHub Pages
6. **Browser Execution**: JavaScript runs directly in browser

## Technology Stack

- **Language**: Java 21
- **Build**: Maven 3.9+ with Wrapper
- **Testing**: JUnit 5 (Jupiter)
- **JS Compiler**: TeaVM 0.10.2
- **CI/CD**: GitHub Actions
- **Hosting**: GitHub Pages

## Development Workflow

### Adding New Features

1. Write Java code in `src/main/java/com/tha2015/jdklearning/`
2. Write tests in `src/test/java/com/tha2015/jdklearning/`
3. Run tests: `./mvnw test`
4. Test locally: `./mvnw package && make pages`
5. Commit and push: Code automatically deploys to GitHub Pages

### Running Tests

```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=MainTest

# Run with detailed output
./mvnw test -X
```

### Previewing Changes

```bash
# Build and open in browser
make pages
```

## Troubleshooting

### Maven Wrapper Not Found

If `./mvnw` doesn't exist, bootstrap it:

```bash
make setup
```

### Docker Permission Denied

On Linux, you may need to add your user to the docker group:

```bash
sudo usermod -aG docker $USER
```

Then log out and back in.

### TeaVM Compilation Errors

TeaVM supports most Java features but has limitations:
- Some JDK APIs are not available in browser
- Native I/O operations don't work
- Check TeaVM documentation for supported APIs

## Contributing

This is a personal learning project, but suggestions are welcome!

## Repository

https://github.com/tha2015/jdk-learning
