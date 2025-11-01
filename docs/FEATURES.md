# Features Module

## Purpose

Feature-specific implementations using MVI.

## Structure

```
features/
  └── [feature-name]/
      ├── data/
      │   ├── repository/
      │   ├── datasource/
      │   └── model/ (DTOs)
      ├── domain/
      │   ├── model/ (domain models)
      │   └── usecase/
      ├── presentation/
      │   ├── ui/
      │   ├── viewmodel/
      │   ├── state/
      │   └── intent/
      └── di/
          └── FeatureModule.kt
```

## MVI Implementation

### Intent
```kotlin
sealed interface FeatureIntent {
    data object LoadData : FeatureIntent
    data class UserAction(val data: String) : FeatureIntent
}
```

### State
```kotlin
data class FeatureState(
    val isLoading: Boolean = false,
    val data: List<Item> = emptyList(),
    val error: String? = null
)
```

### SideEffect
```kotlin
sealed interface FeatureSideEffect {
    data object NavigateBack : FeatureSideEffect
    data class ShowToast(val message: String) : FeatureSideEffect
}
```

### ViewModel
```kotlin
class FeatureViewModel(
    private val useCase: FeatureUseCase
) : ContainerHost<FeatureState, FeatureSideEffect, FeatureIntent> {
    
    override val container: Container<FeatureState, FeatureSideEffect, FeatureIntent> =
        orbitViewModel { FeatureState() }
    
    init {
        loadData()
    }
    
    fun onIntent(intent: FeatureIntent) {
        when (intent) {
            is FeatureIntent.LoadData -> loadData()
            is FeatureIntent.UserAction -> handleAction(intent.data)
        }
    }
    
    private fun loadData() {
        container.intent {
            reduce { copy(isLoading = true) }
            
            useCase.execute()
                .onSuccess { result ->
                    reduce { copy(isLoading = false, data = result) }
                }
                .onFailure { error ->
                    reduce { copy(isLoading = false, error = error.message) }
                    postSideEffect(FeatureSideEffect.ShowToast(error.message ?: "Error"))
                }
        }
    }
}
```

## Usage in UI

```kotlin
@Composable
fun FeatureScreen(viewModel: FeatureViewModel = koinViewModel()) {
    val state by viewModel.container.stateAsState()
    val sideEffect by viewModel.container.sideEffectFlow.collectAsStateWithLifecycle(null)
    
    LaunchedEffect(sideEffect) {
        when (val effect = sideEffect) {
            is FeatureSideEffect.NavigateBack -> navigateBack()
            is FeatureSideEffect.ShowToast -> showToast(effect.message)
            null -> Unit
        }
    }
    
    // UI implementation
    when {
        state.isLoading -> LoadingView()
        state.error != null -> ErrorView(state.error) {
            viewModel.onIntent(FeatureIntent.LoadData)
        }
        else -> DataView(state.data) { data ->
            viewModel.onIntent(FeatureIntent.UserAction(data))
        }
    }
}
```

## Dependencies

- `:core`
