package com.cashcove.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * Base ViewModel class that provides common functionality for all ViewModels.
 * Extend this class to create feature-specific ViewModels.
 *
 * @param initialState The initial state for this ViewModel
 */
abstract class BaseViewModel<State, Intent, SideEffect>(
    initialState: State
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    private val _sideEffect = MutableSharedFlow<SideEffect>()
    val sideEffect: SharedFlow<SideEffect> = _sideEffect.asSharedFlow()

    /**
     * Update the current state
     */
    protected fun updateState(update: State.() -> State) {
        _state.value = _state.value.update()
    }

    /**
     * Post a side effect (navigation, toast, etc.)
     */
    protected fun postSideEffect(effect: SideEffect) {
        viewModelScope.launch {
            _sideEffect.emit(effect)
        }
    }

    /**
     * Get current state
     */
    protected fun currentState(): State = _state.value

    abstract fun onIntent(intent: Intent)
}
