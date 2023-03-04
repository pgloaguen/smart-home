package me.pgloaguen.smarthome.scene.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.pgloaguen.domain.usecase.GetDevices

class HomePageViewModel(private val getDevices: GetDevices) : ViewModel() {

    private val _uiState = MutableStateFlow(HomePageState())
    val uiState: StateFlow<HomePageState> = _uiState.asStateFlow()

    init { loadData() }

    private fun loadData() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val devices = runCatching { getDevices() }
            _uiState.value = _uiState.value.copy(isLoading = false)
            if(devices.isSuccess) {
                _uiState.value = _uiState.value.copy(devices = devices.getOrNull() ?: emptyList())
            } else {
                _uiState.value = _uiState.value.copy(error = devices.exceptionOrNull())
            }
        }
    }

    fun retry() { loadData() }

}