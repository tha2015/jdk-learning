# Claude Context File

This file contains useful information for Claude to understand the project setup and workflow.

## Personal GitHub Account Setup

This project uses wrapper scripts to work with a personal GitHub account (tha2015).

### Git Wrapper Script
**Location**: `~/programs/utils/macos/tha_git.sh`

**Usage**: Use this instead of `git` command
```bash
~/programs/utils/macos/tha_git.sh <git-command>
```

**What it does**:
- Sets Git author name: "Thai H"
- Sets Git author email: "tha2015@users.noreply.github.com"
- Uses SSH key: `~/.ssh/id_rsa_assembla`
- All commits are attributed to the personal GitHub account

### GitHub CLI Wrapper Script
**Location**: `~/programs/utils/macos/tha_gh.sh`

**Usage**: Use this instead of `gh` command
```bash
~/programs/utils/macos/tha_gh.sh <gh-command>
```

**What it does**:
- Uses custom config directory: `~/.config/gh-tha2015`
- Keeps personal GitHub CLI authentication separate from work account

## Environment Details

**Working Directory**: `Project root directory`

**Platform**: macOS (Darwin)

**Java Development**:
- Uses Maven-based build system with Maven Wrapper
- Java 21 (Amazon Corretto 21)
- No local JDK installation required (Docker alternative available)
- TeaVM compiles Java to JavaScript for browser execution

**Build System**:
- Maven 3.9+ with Wrapper (`./mvnw`)
- Bootstrap command: `docker run --rm -v "$(pwd):/app" -w /app maven:3.9-amazoncorretto-21 mvn wrapper:wrapper`
- Primary workflow uses Maven Wrapper directly
- Docker fallback commands available in Makefile

**Testing**:
- JUnit 5 (Jupiter) for unit tests
- Tests run in JVM environment
- Command: `./mvnw test` or `make test`

**JavaScript Compilation**:
- TeaVM 0.10.2 ahead-of-time compiler
- Compiles Java bytecode to JavaScript
- Output: `target/generated/js/`
- Includes HTML page for browser execution

**Makefile Targets**:
- `make` or `make help`: Show available commands
- `make run`: Compile and run Main class
- `make test`: Run JUnit tests
- `make package`: Build everything including TeaVM JavaScript
- `make pages`: Build and preview GitHub Pages locally
- `make clean`: Clean build artifacts
- `make docker-run`: Run using Docker (fallback)
- `make docker-test`: Run tests using Docker (fallback)
- `make docker-package`: Build package using Docker (fallback)
- `make ide`: Open project in Pulsar editor
- `make setup`: Bootstrap Maven Wrapper (one-time)

## Project Structure

```
jdk-learning/
├── .github/
│   └── workflows/
│       └── deploy-pages.yml       # GitHub Actions CI/CD
├── src/
│   ├── main/
│   │   ├── java/com/tha2015/jdklearning/
│   │   │   └── Main.java          # Main Java source
│   │   └── webapp/
│   │       └── index.html         # HTML template for TeaVM
│   └── test/
│       └── java/com/tha2015/jdklearning/
│           └── MainTest.java      # JUnit tests
├── target/                        # Maven build output (gitignored)
│   ├── classes/                   # Compiled Java classes
│   ├── generated/
│   │   └── js/                    # TeaVM JavaScript output
│   │       ├── index.html         # Generated HTML page
│   │       ├── classes.js         # Compiled JavaScript
│   │       └── classes.js.map     # Source map
│   └── jdk-learning-1.0-SNAPSHOT.jar
├── .mvn/                          # Maven Wrapper files
│   └── wrapper/
├── mvnw                           # Maven Wrapper (Unix/Mac)
├── mvnw.cmd                       # Maven Wrapper (Windows)
├── pom.xml                        # Maven configuration
├── Makefile                       # Build shortcuts
├── .gitignore                     # Git ignore rules
├── README.md                      # User-facing documentation
└── CLAUDE.md                      # This file - context for Claude
```

## Development Workflow

### Standard Development (Maven Wrapper)

```bash
# Compile Java sources
./mvnw compile

# Run main application
./mvnw exec:java

# Run tests
./mvnw test

# Build everything (including TeaVM JavaScript)
./mvnw clean package

# Preview in browser
open target/generated/js/index.html
```

### Using Makefile Shortcuts

```bash
# Run application
make run

# Run tests
make test

# Build package
make package

# Preview GitHub Pages
make pages

# Clean build
make clean
```

### Docker Fallback (No Maven Wrapper)

```bash
# Run in Docker
make docker-run

# Test in Docker
make docker-test

# Package in Docker
make docker-package
```

### Opening in Editor

```bash
make ide
# Or: pulsar .
```

### Git Operations

Always use the wrapper scripts for git operations:

```bash
# Examples:
~/programs/utils/macos/tha_git.sh status
~/programs/utils/macos/tha_git.sh add .
~/programs/utils/macos/tha_git.sh commit -m "message"
~/programs/utils/macos/tha_git.sh push
```

### GitHub CLI Operations

```bash
# Examples:
~/programs/utils/macos/tha_gh.sh repo view
~/programs/utils/macos/tha_gh.sh workflow list
~/programs/utils/macos/tha_gh.sh run watch
```

## GitHub Repository

- **URL**: https://github.com/tha2015/jdk-learning
- **Visibility**: Private
- **Owner**: tha2015 (Thai H)
- **GitHub Pages**: https://tha2015.github.io/jdk-learning/

## GitHub Actions CI/CD

**Workflow File**: `.github/workflows/deploy-pages.yml`

**Triggers**:
- Push to main/master branch
- Manual dispatch from Actions tab

**Process**:
1. Checkout code
2. Set up Java 21 (Corretto)
3. Build with Maven (`./mvnw clean package`)
4. Verify TeaVM output
5. Upload `target/generated/js/` as Pages artifact
6. Deploy to GitHub Pages

**Deployment**:
- Uses official GitHub Pages actions
- Deploys to `github-pages` environment
- Automatic on push to main
- View status: https://github.com/tha2015/jdk-learning/actions

## Technology Stack

- **Language**: Java 21
- **Package Manager**: Maven 3.9+ (via Wrapper)
- **Testing**: JUnit 5 (Jupiter)
- **JS Compiler**: TeaVM 0.10.2
- **Runtime**: JVM (development) + Browser (production)
- **CI/CD**: GitHub Actions
- **Hosting**: GitHub Pages
- **Container**: Docker (fallback/bootstrap)

## Maven Configuration

**Package Structure**: `com.tha2015.jdklearning`

**Main Dependencies**:
- `org.teavm:teavm-classlib:0.10.2` (provided)
- `org.teavm:teavm-jso-apis:0.10.2` (provided)
- `org.junit.jupiter:junit-jupiter:5.11.3` (test)

**Key Plugins**:
- `maven-compiler-plugin:3.13.0` (Java 21 compilation)
- `maven-surefire-plugin:3.5.2` (JUnit 5 testing)
- `exec-maven-plugin:3.5.0` (Run Main class)
- `teavm-maven-plugin:0.10.2` (JavaScript compilation)

**Build Phases**:
1. `compile` - Compile Java to bytecode
2. `test` - Run JUnit tests
3. `prepare-package` - Run TeaVM compilation
4. `package` - Create JAR and finalize JS output

## TeaVM Configuration

**Main Class**: `com.tha2015.jdklearning.Main`

**Output Directory**: `target/generated/js/`

**Target Type**: JAVASCRIPT

**Optimization**: SIMPLE (balances size and build time)

**Features Enabled**:
- Source maps generation
- Minification
- Debug information

**HTML Template**: `src/main/webapp/index.html`

**Browser APIs**:
- Uses `teavm-jso-apis` for browser DOM access
- Can interact with `Window`, `Document`, `Console`
- System.out.println() redirected to browser console and page

## GitHub Pages Setup

**Source**: GitHub Actions

**Branch**: Deployed from workflow (no gh-pages branch)

**Path**: `target/generated/js/` directory

**URL**: https://tha2015.github.io/jdk-learning/

**Custom Domain**: Not configured

**HTTPS**: Enforced (default)

**Configuration Steps**:
1. Go to Repository Settings → Pages
2. Source: Select "GitHub Actions"
3. Verify workflow permissions: Settings → Actions → General
4. Ensure "Read and write permissions" is selected

## Notes for Claude

- Always use the full paths to `tha_git.sh` and `tha_gh.sh` when performing git/gh operations
- This ensures all commits are properly attributed to the personal account
- The user typically sets up aliases locally: `alias git=tha_git.sh` and `alias gh=tha_gh.sh`
- Do not assume `git` or `gh` commands will work with the personal account - always use the wrapper scripts
- Maven Wrapper (`./mvnw`) is the primary build tool
- Docker commands are fallback options
- TeaVM has limitations - not all Java APIs work in browser
- GitHub Pages deploys automatically on push to main
- Java 21 is used but TeaVM supports a subset of APIs
- Always test both JVM (`./mvnw exec:java`) and browser (`make pages`) execution

## Common Commands Reference

### Development
```bash
./mvnw compile exec:java          # Run in JVM
./mvnw test                        # Run tests
./mvnw package                     # Build + TeaVM
make pages                         # Preview in browser
```

### Building
```bash
./mvnw clean                       # Clean
./mvnw compile                     # Compile only
./mvnw package                     # Full build
./mvnw package -DskipTests         # Build without tests
```

### Testing
```bash
./mvnw test                        # Run all tests
./mvnw test -Dtest=MainTest        # Run specific test
./mvnw test -X                     # Verbose output
```

### Deployment
```bash
# Commit and push (uses git wrapper)
~/programs/utils/macos/tha_git.sh add .
~/programs/utils/macos/tha_git.sh commit -m "message"
~/programs/utils/macos/tha_git.sh push

# GitHub Actions automatically:
# - Builds project
# - Compiles to JavaScript
# - Deploys to GitHub Pages
```

### Monitoring
```bash
# Watch GitHub Actions workflow
~/programs/utils/macos/tha_gh.sh run watch

# View workflow logs
~/programs/utils/macos/tha_gh.sh run view --log

# Check repository status
~/programs/utils/macos/tha_gh.sh repo view
```
