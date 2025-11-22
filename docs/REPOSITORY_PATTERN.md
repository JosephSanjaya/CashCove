# Repository Pattern Implementation Guide

This document explains the repository pattern implementation in this project, following Android architecture best practices and industry standards.

## Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                        UI Layer                              │
│  ┌──────────┐      ┌──────────┐      ┌──────────┐         │
│  │ViewModel │─────▶│ViewModel │─────▶│ViewModel │         │
│  └────┬─────┘      └────┬─────┘      └────┬─────┘         │
└───────┼─────────────────┼─────────────────┼───────────────┘
        │                 │                 │
        ▼                 ▼                 ▼
┌─────────────────────────────────────────────────────────────┐
│                      Data Layer                              │
│  ┌────────────┐   ┌────────────┐   ┌────────────┐         │
│  │ Repository │   │ Repository │   │ Repository │         │
│  │   (Impl)   │   │   (Impl)   │   │   (Impl)   │         │
│  └──────┬─────┘   └──────┬─────┘   └──────┬─────┘         │
│         │                │                │                  │
│         └────────────────┴────────────────┘                 │
│                          │                                   │
│                    ┌─────▼──────┐                           │
│                    │BaseReposit.│◀──RepositoryDependencies  │
│                    └─────┬──────┘                           │
│                          │                                   │
│         ┌────────────────┼────────────────┐                │
│         ▼                ▼                ▼                 │
│  ┌──────────┐    ┌──────────┐    ┌──────────┐            │
│  │  Remote  │    │  Local   │    │  Cache   │            │
│  │   API    │    │    DB    │    │          │            │
│  └──────────┘    └──────────┘    └──────────┘            │
└─────────────────────────────────────────────────────────────┘
```

## Core Components

### 1. BaseRepository
**Location:** `core/src/main/java/com/cashcove/core/common/repository/BaseRepository.kt`

```kotlin
abstract class BaseRepository(
    private val dependencies: RepositoryDependencies
) {
    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): UiState<T>
}
```

**Purpose:**
- Provides common functionality for all repositories
- Handles API calls with automatic error handling
- Manages connectivity checking and logging
- Eliminates boilerplate code

**Benefits:**
✅ Single responsibility - data access only
✅ Consistent error handling across app
✅ Easy to test with mocked dependencies
✅ DRY principle - write once, use everywhere

### 2. RepositoryDependencies
**Location:** `core/src/main/java/com/cashcove/core/di/RepositoryModule.kt`

```kotlin
data class RepositoryDependencies(
    val errorHandler: ErrorHandler,
    val connectivityChecker: ConnectivityChecker,
    val logger: Logger
)
```

**Purpose:**
- Bundles all infrastructure dependencies
- Provided as a singleton by Koin
- Automatically injected into all repositories
- Follows Dependency Inversion Principle

**Benefits:**
✅ One dependency instead of three
✅ Centralized configuration
✅ Type-safe dependency injection
✅ Easy to mock in tests

### 3. RepositoryModule
**Location:** `core/src/main/java/com/cashcove/core/di/RepositoryModule.kt`

Provides:
- `RepositoryDependencies` - Singleton with error handler, connectivity checker, and logger
- `Ktorfit` - Shared HTTP client configured with interceptors and JSON serialization

**Benefits:**
✅ No duplication of Ktorfit setup across features
✅ Consistent API configuration
✅ Single source of truth
✅ Automatic dependency resolution

## Implementation Guide

### Creating a New Repository

#### Step 1: Define Repository Interface

```kotlin
interface MyFeatureRepository {
    suspend fun getData(): UiState<MyData>
    suspend fun postData(data: MyData): UiState<Unit>
}
```

#### Step 2: Create Repository Implementation

```kotlin
class MyFeatureRepositoryImpl(
    private val myFeatureService: MyFeatureService,
    dependencies: RepositoryDependencies
) : BaseRepository(dependencies), MyFeatureRepository {
    
    override suspend fun getData(): UiState<MyData> {
        return safeApiCall {
            myFeatureService.getData()
        }
    }
    
    override suspend fun postData(data: MyData): UiState<Unit> {
        return safeApiCall {
            myFeatureService.postData(data)
        }
    }
}
```

**That's it!** No need to:
- ❌ Pass errorHandler, connectivityChecker, logger manually
- ❌ Write try-catch blocks
- ❌ Handle connectivity checks
- ❌ Log errors
- ❌ Convert exceptions to UiState

#### Step 3: Register in Feature Module

```kotlin
val MyFeatureModule = module {
    // Create service from shared Ktorfit
    single<MyFeatureService> {
        val ktorfit: Ktorfit = get()  // Shared from RepositoryModule
        ktorfit.create()
    }

    // Register repository - dependencies injected automatically
    single<MyFeatureRepository> {
        MyFeatureRepositoryImpl(
            myFeatureService = get(),
            dependencies = get()  // ✅ Automatic injection
        )
    }

    // ViewModels
    viewModel { MyFeatureViewModel(get()) }
}
```

## Best Practices

### ✅ DO

1. **Use interfaces for repositories**
   ```kotlin
   interface UserRepository
   class UserRepositoryImpl : UserRepository
   ```

2. **Keep repositories UI-agnostic**
   ```kotlin
   // ❌ Bad
   class MyRepo(private val context: Context)
   
   // ✅ Good
   class MyRepo(dependencies: RepositoryDependencies)
   ```

3. **Use suspend functions for one-shot operations**
   ```kotlin
   suspend fun login(email: String): UiState<User>
   ```

4. **Use Flow for continuous data streams**
   ```kotlin
   fun observeUser(): Flow<UiState<User>>
   ```

5. **Let repositories focus on data access only**
   ```kotlin
   // ✅ Good - data access
   suspend fun getUsers(): UiState<List<User>>
   
   // ❌ Bad - business logic
   suspend fun validateAndSaveUser(user: User): UiState<Boolean>
   ```

### ❌ DON'T

1. **Don't pass UI dependencies to repositories**
   ```kotlin
   // ❌ Bad
   class MyRepo(private val context: Context, private val lifecycleOwner: LifecycleOwner)
   ```

2. **Don't put business logic in repositories**
   ```kotlin
   // ❌ Bad - validation is business logic
   suspend fun saveUser(user: User): UiState<Unit> {
       if (!user.email.contains("@")) return UiState.Error("Invalid email")
       return safeApiCall { api.saveUser(user) }
   }
   ```

3. **Don't duplicate Ktorfit setup**
   ```kotlin
   // ❌ Bad - creates new Ktorfit
   val ktorfit = Ktorfit.Builder()...
   
   // ✅ Good - use shared instance
   val ktorfit: Ktorfit = get()
   ```

4. **Don't manually handle errors**
   ```kotlin
   // ❌ Bad
   try {
       val result = api.getData()
       UiState.Success(result)
   } catch (e: Exception) {
       UiState.Error(e.message)
   }
   
   // ✅ Good
   safeApiCall { api.getData() }
   ```

## Testing

### Unit Testing Repositories

```kotlin
class MyRepositoryTest {
    private lateinit var repository: MyFeatureRepository
    private val mockService: MyFeatureService = mockk()
    private val mockDeps: RepositoryDependencies = mockk {
        every { errorHandler } returns mockk()
        every { connectivityChecker } returns mockk {
            coEvery { isConnected() } returns true
        }
        every { logger } returns mockk(relaxed = true)
    }

    @Before
    fun setup() {
        repository = MyFeatureRepositoryImpl(mockService, mockDeps)
    }

    @Test
    fun `getData returns success when API call succeeds`() = runTest {
        // Given
        val expected = MyData("test")
        coEvery { mockService.getData() } returns expected

        // When
        val result = repository.getData()

        // Then
        assertTrue(result is UiState.Success)
        assertEquals(expected, (result as UiState.Success).data)
    }
}
```

## Migration Guide

### From Old Pattern to New Pattern

**Before:**
```kotlin
class OldRepository(
    private val service: MyService,
    private val errorHandler: ErrorHandler,
    private val connectivityChecker: ConnectivityChecker,
    private val logger: Logger
) {
    suspend fun getData(): UiState<Data> {
        return safeApiCall(errorHandler, connectivityChecker, logger) {
            service.getData()
        }
    }
}
```

**After:**
```kotlin
class NewRepository(
    private val service: MyService,
    dependencies: RepositoryDependencies
) : BaseRepository(dependencies) {
    suspend fun getData(): UiState<Data> {
        return safeApiCall {
            service.getData()
        }
    }
}
```

**Changes needed:**
1. Extend `BaseRepository`
2. Accept `RepositoryDependencies` instead of individual deps
3. Use inherited `safeApiCall` method

## Summary

This implementation follows:
- ✅ Android Architecture Components guidelines
- ✅ Clean Architecture principles
- ✅ SOLID principles (especially SRP and DIP)
- ✅ Kotlin coroutines best practices
- ✅ Dependency Injection best practices
- ✅ DRY principle

**Benefits for developers:**
- 🚀 Faster development - less boilerplate
- 🧪 Easier testing - mocked dependencies
- 🔧 Maintainable - changes in one place
- 📏 Consistent - same pattern everywhere
- 🛡️ Robust - centralized error handling

