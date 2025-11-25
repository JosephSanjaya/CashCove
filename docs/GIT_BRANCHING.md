# Git Branching Strategy

## Overview

Simple branching strategy for two-person team: Developer + Reviewer.

## Branches

### `main`
- Production-ready code
- Protected branch
- Only merges via Pull Request (PR)
- Always deployable

### `develop` (optional)
- Integration branch
- Can be used for staging/QA
- Merges to `main` via PR

### Feature branches
- `feature/[feature-name]`
- Created from `main` (or `develop`)
- Developer works here
- Merges to `main` via PR after review

## Workflow

1. **Developer**: Create feature branch
   ```bash
   git checkout main
   git pull origin main
   git checkout -b feature/authentication
   ```

2. **Developer**: Work and commit
   ```bash
   git add .
   git commit -m "feat(auth): add login screen"
   ```

3. **Developer**: Push and create PR
   ```bash
   git push origin feature/authentication
   # Create PR on GitHub/GitLab
   ```

4. **Reviewer**: Review PR, approve/request changes

5. **Developer**: Address review comments
   ```bash
   # Make changes
   git commit -m "fix(auth): address review comments"
   git push origin feature/authentication
   ```

6. **Reviewer**: Approve and merge PR

7. **Developer**: Delete branch and update local
   ```bash
   git checkout main
   git pull origin main
   git branch -d feature/authentication
   ```

## PR Requirements

- Code review approval required
- All CI checks pass
- No merge conflicts
- Descriptive commit messages

## Branch Naming

- `feature/authentication`
- `feature/payment-flow`
- `fix/crash-on-startup`
- `refactor/api-client`

## Hotfixes

- `hotfix/critical-bug`
- Created from `main`
- Merged directly to `main`
- Back-merged to `develop` if exists
