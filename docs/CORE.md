# Core Module

## Purpose

Shared utilities, network, datastore, UI components.

## Structure

```
core/
  ├── common/
  │   └── AppConstants.kt
  ├── datastore/
  │   └── [datastore implementations]
  ├── di/
  │   ├── DatabaseModule.kt
  │   ├── NetworkModule.kt
  │   └── RepositoryModule.kt
  ├── network/
  │   └── [API interfaces, clients]
  └── ui/
      └── theme/
          ├── Color.kt
          ├── Theme.kt
          └── Type.kt
```

## Responsibilities

- API clients (Ktorfit)
- DataStore/MMKV
- Shared UI components
- Theme system
- Common utilities
- Base classes/interfaces
- Error handling

## Dependencies

- None (base module)
