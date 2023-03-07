package me.pgloaguen.smarthome.scene.devicesteering

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.pgloaguen.domain.model.DeviceId
import me.pgloaguen.domain.usecase.GetDevices

class DeviceSteeringViewModel(
    private val deviceId: DeviceId,
    private val getDevices: GetDevices,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DeviceSteeringState())
    val uiState: StateFlow<DeviceSteeringState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val deviceResult = runCatching { getDevices().first { it.id == deviceId } }
            if (deviceResult.isSuccess) {
                _uiState.value = _uiState.value.copy(device = deviceResult.getOrThrow(), isLoading = false)
            } else {
                _uiState.value =
                    _uiState.value.copy(error = deviceResult.exceptionOrNull(), isLoading = false)
            }
        }
    }

    fun retry() {
        loadData()
    }

}