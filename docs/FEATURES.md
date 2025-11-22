# Features Module Reference Guide

## Purpose

Feature-specific implementations using MVI pattern. Each feature module is self-contained with its own data, domain, and presentation layers following Clean Architecture principles.

## Complete Feature Structure

```
features/
  └── [feature-name]/
      ├── data/
      │   ├── repository/
      │   │   ├── [Feature]Repository.kt (interface)
      │   │   └── [Feature]RepositoryImpl.kt
      │   ├── datasource/
      │   │   ├── [Feature]RemoteDataSource.kt (interface)
      │   │   ├── [Feature]RemoteDataSourceImpl.kt
      │   │   ├── [Feature]LocalDataSource.kt (interface, optional)
      │   │   └── [Feature]LocalDataSourceImpl.kt (optional)
      │   ├── service/
      │   │   └── [Feature]Service.kt (Ktorfit interface)
      │   └── model/
      │       └── [Model]Dto.kt (DTOs - Data Transfer Objects)
      ├── domain/
      │   ├── model/
      │   │   └── [Model].kt (domain models - business entities)
      │   └── usecase/
      │       └── [Action]UseCase.kt
      ├── presentation/
      │   ├── [screen-name]/
      │   │   ├── [Screen]Screen.kt
      │   │   ├── [Screen]ViewModel.kt
      │   │   ├── [Screen]State.kt
      │   │   ├── [Screen]Intent.kt
      │   │   └── [Screen]SideEffect.kt
      │   └── ...
      ├── navigation/
      │   ├── [Feature]NavGraph.kt
      │   └── [Feature]Screen.kt
      └── di/
          └── [Feature]Module.kt
```

## Data Layer

### What Goes in `data/datasource`?

DataSources are abstractions that handle direct interaction with data sources:

- **RemoteDataSource**: Interfaces and implementations that use Service (Ktorfit) to fetch data from remote APIs
- **LocalDataSource**: Interfaces and implementations that use DAOs to fetch/store data in local database
- **CacheDataSource**: (Optional) Interfaces for caching strategies

DataSources return raw data types (DTOs or Entities). Error handling and `UiState` wrapping happens in the Repository layer.

### What Goes in `data/model`?

All Data Transfer Objects (DTOs) that match the API structure:
- Request DTOs: `SendOtpRequestDto.kt`, `VerifyOtpRequestDto.kt`, etc.
- Response DTOs: `SendOtpResponseDto.kt`, `UserDto.kt`, `TokenDto.kt`, etc.

**Flat structure** - no subdirectories needed. All DTOs go directly in `data/model/`.

### Service Interface (Ktorfit)

```kotlin
interface AuthenticationService {
    @POST("auth/send-otp")
    suspend fun sendOtp(@Body request: SendOtpRequestDto): SendOtpResponseDto
    
    @GET("auth/me")
    suspend fun getCurrentUser(): GetUserResponseDto
}
```

### DataSource

DataSources abstract the data fetching logic from repositories. They handle direct interaction with data sources (remote API, local database, etc.).

**Remote DataSource** (uses Service):
```kotlin
interface AuthenticationRemoteDataSource {
    suspend fun sendOtp(request: SendOtpRequestDto): SendOtpResponseDto
    suspend fun getCurrentUser(): GetUserResponseDto
}

class AuthenticationRemoteDataSourceImpl(
    private val service: AuthenticationService
) : AuthenticationRemoteDataSource {
    
    override suspend fun sendOtp(request: SendOtpRequestDto): SendOtpResponseDto =
        service.sendOtp(request)
    
    override suspend fun getCurrentUser(): GetUserResponseDto =
        service.getCurrentUser()
}
```

Note: DataSources return raw data. The Repository wraps these calls with `safeApiCall` to convert them to `UiState`.

**Local DataSource** (uses DAOs, optional):
```kotlin
interface AuthenticationLocalDataSource {
    suspend fun saveUser(user: UserEntity)
    suspend fun getUser(id: String): UserEntity?
}

class AuthenticationLocalDataSourceImpl(
    private val userDao: UserDao
) : AuthenticationLocalDataSource {
    
    override suspend fun saveUser(user: UserEntity) {
        userDao.insert(user)
    }
    
    override suspend fun getUser(id: String): UserEntity? {
        return userDao.getUserById(id)
    }
}
```

### Repository Interface

```kotlin
interface AuthenticationRepository {
    suspend fun sendOtp(request: SendOtpRequestDto): UiState<SendOtpResponseDto>
    suspend fun getCurrentUser(): UiState<GetUserResponseDto>
}
```

### Repository Implementation

Repositories orchestrate data sources and handle business logic like caching, data transformation, etc.

```kotlin
class AuthenticationRepositoryImpl(
    private val remoteDataSource: AuthenticationRemoteDataSource,
    private val localDataSource: AuthenticationLocalDataSource? = null,
    dependencies: RepositoryDependencies
) : BaseRepository(dependencies), AuthenticationRepository {
    
    override suspend fun sendOtp(request: SendOtpRequestDto): UiState<SendOtpResponseDto> =
        safeApiCall { remoteDataSource.sendOtp(request) }
    
    override suspend fun getCurrentUser(): UiState<GetUserResponseDto> {
        // Try local first, then remote
        localDataSource?.getUser(userId)?.let { 
            return UiState.Success(mapToDto(it))
        }
        return safeApiCall { remoteDataSource.getCurrentUser() }
    }
}
```

Note: Repository wraps DataSource calls with `safeApiCall` to handle errors and convert to `UiState`.

### Data Models (DTOs)

Data Transfer Objects that match the API structure. All DTOs go directly in `data/model/` without subdirectories.

- `SendOtpRequestDto.kt`
- `SendOtpResponseDto.kt`
- `VerifyOtpRequestDto.kt`
- `VerifyOtpResponseDto.kt`
- `UserDto.kt`
- `TokenDto.kt`
- etc.

Located in `data/model/` (flat structure, no request/response subdirectories)

## Domain Layer

### What Goes in `domain/model`?

Pure business entities that represent core concepts of your domain. These are independent of:
- API structure (DTOs)
- Database schema (Entities)
- UI representation (State)

**What belongs here:**
- Business entities: `User.kt`, `Product.kt`, `Order.kt`, etc.
- Value objects: `Email.kt`, `PhoneNumber.kt`, `Money.kt`, etc.
- Domain-specific enums and sealed classes
- Business rules and validation logic within the models
- Mapper functions/extensions to convert DTOs ↔ Domain models

**What does NOT belong here:**
- DTOs (those go in `data/model`)
- Database entities (those go in `core/database/entity`)
- UI state classes (those go in `presentation/[screen]/[Screen]State.kt`)

### Domain Models

Domain models should contain only business logic and validation rules.

```kotlin
data class User(
    val id: String,
    val phoneNumber: String,
    val name: String,
    val email: String?
) {
    val isEmailVerified: Boolean
        get() = email != null && email.isNotBlank()
    
    fun validate(): Boolean {
        return phoneNumber.isNotBlank() && name.isNotBlank()
    }
}

data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: Long
) {
    val isExpired: Boolean
        get() = System.currentTimeMillis() >= expiresAt
}
```

**What goes in domain/model:**
- Business entities (User, Product, Order, etc.)
- Value objects (Email, PhoneNumber, Money, etc.)
- Domain-specific types and enums
- Business rules and validation logic
- Mappers to convert between DTOs ↔ Domain models

### Use Cases

Business logic that orchestrates repository calls and transforms DTOs to domain models:

```kotlin
class LoginUseCase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(phoneNumber: String): Flow<UiState<Login>> = flow {
        emit(UiState.Loading)
        val login = Login(phoneNumber)
        if (login.validate()) {
            when (val result = repository.sendOtp(SendOtpRequestDto(phoneNumber))) {
                is UiState.Success -> {
                    // Transform DTO to domain model if needed
                    emit(UiState.Success(login))
                }
                is UiState.Error -> emit(result)
                else -> {}
            }
        } else {
            emit(UiState.Error("Phone number validation failed"))
        }
    }
}
```

**Where Use Cases Are Used:**
- Use cases are called from **ViewModels** (not from repositories or data sources)
- ViewModels should use use cases instead of calling repositories directly
- This keeps business logic in the domain layer and presentation logic in the ViewModel

## Presentation Layer

### State

Immutable UI state:

```kotlin
data class LoginState(
    val phoneNumber: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
```

### Intent

User actions/events:

```kotlin
sealed interface LoginIntent {
    data class UpdatePhoneNumber(val phoneNumber: String) : LoginIntent
    data class SendOtp(val phoneNumber: String) : LoginIntent
    data object NavigateToRegister : LoginIntent
}
```

### SideEffect

One-time events (navigation, toasts):

```kotlin
sealed interface LoginSideEffect {
    data object NavigateToRegister : LoginSideEffect
    data object NavigateToOtpVerification : LoginSideEffect
    data class ShowToast(val message: String) : LoginSideEffect
}
```

### ViewModel

**ViewModels use Use Cases, not Repositories directly.**

```kotlin
class LoginViewModel(
    initialState: LoginState = LoginState(),
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginState, LoginIntent, LoginSideEffect>(initialState) {

    fun onIntent(intent: LoginIntent) = reduce(intent)

    override fun reduce(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.SendOtp -> {
                viewModelScope.launch {
                    updateState { copy(isLoading = true) }
                    loginUseCase(intent.phoneNumber).collect { result ->
                        when (result) {
                            is UiState.Success -> {
                                updateState { copy(isLoading = false) }
                                postSideEffect(LoginSideEffect.NavigateToOtpVerification)
                            }
                            is UiState.Error -> {
                                updateState { 
                                    copy(isLoading = false, error = result.message) 
                                }
                            }
                            is UiState.Loading -> {
                                updateState { copy(isLoading = true) }
                            }
                            is UiState.Idle -> {}
                        }
                    }
                }
            }
            is LoginIntent.UpdatePhoneNumber -> {
                updateState { copy(phoneNumber = intent.phoneNumber) }
            }
            // ... handle other intents
        }
    }
}
```

**Key Points:**
- ViewModels receive **Use Cases** as dependencies, not Repositories
- Use cases handle business logic and validation
- ViewModels only handle UI state management and side effects
- This separation keeps business logic in the domain layer

### Screen (Composable)

```kotlin
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onNavigateToRegister: () -> Unit,
    onNavigateToOtp: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val sideEffect by viewModel.sideEffect.collectAsStateWithLifecycle(null)

    LaunchedEffect(sideEffect) {
        when (val effect = sideEffect) {
            is LoginSideEffect.NavigateToRegister -> onNavigateToRegister()
            is LoginSideEffect.NavigateToOtpVerification -> onNavigateToOtp()
            null -> Unit
        }
    }

    // UI implementation
}
```

## Navigation

### Screen Definitions

```kotlin
sealed class AuthenticationScreen(val route: String) {
    data object Login : AuthenticationScreen("auth/login")
    data object Register : AuthenticationScreen("auth/register")
    data object Otp : AuthenticationScreen("auth/otp")
}
```

### NavGraph

```kotlin
@Composable
fun AuthenticationNavGraph(
    navController: NavHostController,
    startDestination: String = AuthenticationScreen.Login.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(AuthenticationScreen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(AuthenticationScreen.Register.route) }
            )
        }
        // ... other screens
    }
}
```

## Dependency Injection

### Feature Module

```kotlin
val AuthenticationModule = module {
    // Service - uses shared Ktorfit from core
    single<AuthenticationService> {
        get<Ktorfit>().create()
    }

    // Remote DataSource
    single<AuthenticationRemoteDataSource> {
        AuthenticationRemoteDataSourceImpl(
            service = get(),
            dependencies = get()
        )
    }

    // Local DataSource (optional)
    single<AuthenticationLocalDataSource> {
        AuthenticationLocalDataSourceImpl(
            userDao = get()
        )
    }

    // Repository - orchestrates data sources
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            dependencies = get()
        )
    }

    // Use Cases
    factory { LoginUseCase(repository = get()) }
    factory { VerifyOtpUseCase(repository = get()) }

    // ViewModels - inject Use Cases, not Repositories
    viewModel { LoginViewModel(initialState = LoginState(), loginUseCase = get()) }
    viewModel { RegisterViewModel(initialState = RegisterState(), registerUseCase = get()) }
}
```

## Quick Reference Checklist

### ✅ Data Layer
- [ ] Service interface with Ktorfit annotations
- [ ] Remote DataSource interface and implementation
- [ ] Local DataSource interface and implementation (optional)
- [ ] Repository interface
- [ ] Repository implementation extending `BaseRepository`
- [ ] DTOs in `data/model/` (flat structure, no subdirectories)

### ✅ Domain Layer
- [ ] Domain models (pure business entities with business logic)
- [ ] Value objects (if applicable)
- [ ] Use cases for business logic
- [ ] Mappers to convert DTOs ↔ Domain models

### ✅ Presentation Layer
- [ ] State (data class)
- [ ] Intent (sealed interface)
- [ ] SideEffect (sealed interface)
- [ ] ViewModel extending `BaseViewModel`
- [ ] Screen composable

### ✅ Navigation
- [ ] Screen sealed class with routes
- [ ] NavGraph composable

### ✅ Dependency Injection
- [ ] Feature module with service, repository, use cases, viewmodels

## Data Flow with Use Cases

The complete data flow in the application:

```
Screen → Intent → ViewModel → UseCase → Repository → DataSource → Service/DAO
       ← State ←            ← UiState ←            ← DTO/Entity ←
       ← SideEffect ←
```

**Where Use Cases Are Used:**
1. **ViewModels call Use Cases** - ViewModels should never call Repositories directly
2. **Use Cases call Repositories** - Use cases orchestrate repository calls
3. **Use Cases contain business logic** - Validation, transformation, business rules
4. **Use Cases return domain models** - Transform DTOs to domain models before returning

**Example Flow:**
```kotlin
// Screen
Button(onClick = { viewModel.onIntent(LoginIntent.SendOtp(phoneNumber)) })

// ViewModel
override fun reduce(intent: LoginIntent) {
    when (intent) {
        is LoginIntent.SendOtp -> {
            viewModelScope.launch {
                loginUseCase(intent.phoneNumber).collect { result ->
                    // Handle UiState and update UI
                }
            }
        }
    }
}

// UseCase
suspend operator fun invoke(phoneNumber: String): Flow<UiState<Login>> {
    // Business logic: validation, transformation
    val login = Login(phoneNumber)
    if (login.validate()) {
        return repository.sendOtp(...)
    }
}

// Repository
suspend fun sendOtp(request: SendOtpRequestDto): UiState<SendOtpResponseDto> {
    return safeApiCall { remoteDataSource.sendOtp(request) }
}
```

## Best Practices

1. **DataSource**: Abstracts data fetching from repositories. RemoteDataSource uses Service, LocalDataSource uses DAOs
2. **Repository**: Always extend `BaseRepository` and use `safeApiCall`. Orchestrates data sources, handles caching strategy
3. **DTOs**: Keep in `data/model/` flat structure. Match API structure exactly
4. **Domain Models**: Pure business entities with business logic. Independent of API/database structure
5. **Use Cases**: 
   - Put business logic here, not in ViewModel
   - Transform DTOs to domain models
   - Called by ViewModels, not by Repositories
   - One use case per business operation
6. **State**: Keep it immutable and minimal
7. **Intent**: One intent per user action
8. **SideEffect**: Use for navigation and one-time events only
9. **ViewModel**: 
   - Only UI state management and side effects
   - Calls Use Cases, never Repositories directly
   - No business logic
10. **Models**: Separate DTOs (data layer) from domain models (domain layer)

## Dependencies

- `:core` - Base classes, utilities, network, repository infrastructure
