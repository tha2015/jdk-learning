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
- Uses Docker with Amazon Corretto 21 image
- No local JDK installation required
- Compilation and execution happen inside Docker container
- Docker command mounts current directory as `/app` inside container

**Makefile Targets**:
- `make run` (or just `make`): Compiles Main.java and runs it in Docker
- `make ide`: Opens project in Pulsar editor

## Project Structure

```
jdk-learning/
├── Main.java       # Main Java source file
├── Makefile        # Build automation using Docker
├── out/            # Compiled classes (gitignored)
├── README.md       # User-facing documentation
└── CLAUDE.md       # This file - context for Claude
```

## Development Workflow

### Running the Project
```bash
make
```
This uses Docker with Amazon Corretto 21 to compile and run Main.java

### Opening in Editor
```bash
pulsar .
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
~/programs/utils/macos/tha_gh.sh repo create <name> --private
~/programs/utils/macos/tha_gh.sh repo view
```

## GitHub Repository
- **URL**: https://github.com/tha2015/jdk-learning
- **Visibility**: Private
- **Owner**: tha2015 (Thai H)

## Notes for Claude
- Always use the full paths to tha_git.sh and tha_gh.sh when performing git/gh operations
- This ensures all commits are properly attributed to the personal account
- The user typically sets up aliases locally: `alias git=tha_git.sh` and `alias gh=tha_gh.sh`
- Do not assume `git` or `gh` commands will work with the personal account - always use the wrapper scripts
