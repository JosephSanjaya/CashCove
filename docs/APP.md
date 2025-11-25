# App Module

## Purpose

Entry point, navigation, DI initialization.

## Structure

```
app/
  ├── MainActivity.kt
  ├── CashCoveApp.kt
  ├── di/
  │   └── AppModule.kt
  └── navigation/
      └── NavGraph.kt
```

## Responsibilities

- Initialize Koin DI
- Setup Compose Navigation
- Configure app-wide settings
- Theme/Edge-to-edge setup
- Deep linking

## Dependencies

- `:core`
- `:features:*`
