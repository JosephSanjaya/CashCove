# Architecture Overview

## Modules

- **app**: Entry point, navigation, DI initialization
- **core**: Shared utilities, network, datastore, UI components
- **features**: Feature modules (e.g., authentication)

## MVI Pattern

**Intent** → **ViewModel** → **State** → **View**

- **Intent**: User actions (events from UI)
- **State**: Immutable UI state (data class)
- **SideEffect**: One-time events (navigation, toasts)

## Data Flow

```
View → Intent → ViewModel → UseCase → Repository → DataSource
       ← State ←            ← Result ←              ← Network/Local
       ← SideEffect ←
```

## Feature Structure

```
feature/
  ├── data/
  │   ├── repository/
  │   ├── datasource/
  │   └── model/ (DTOs)
  ├── domain/
  │   ├── model/ (domain models)
  │   └── usecase/
  └── presentation/
      ├── ui/
      ├── viewmodel/
      ├── state/
      └── intent/
```
