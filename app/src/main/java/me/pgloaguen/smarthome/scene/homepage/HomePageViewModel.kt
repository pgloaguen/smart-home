package me.pgloaguen.smarthome.scene.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.pgloaguen.domain.model.Device
import me.pgloaguen.domain.usecase.DeleteDevice
import me.pgloaguen.domain.usecase.GetDevices

class HomePageViewModel(
    private val getDevices: GetDevices,
    private val deleteDevice: DeleteDevice
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomePageState())
    val uiState: StateFlow<HomePageState> = _uiState.asStateFlow()
    private var allDevices = emptyList<Device>()

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val devices = runCatching { getDevices() }
            if (devices.isSuccess) {
                allDevices = devices.getOrNull() ?: emptyList()
                _uiState.value =
                    _uiState.value.copy(devices = filterAllDeviceByType(), isLoading = false)
            } else {
                _uiState.value =
                    _uiState.value.copy(error = devices.exceptionOrNull(), isLoading = false)
            }
        }
    }

    private fun filterAllDeviceByType(
        lightFilter: Boolean = uiState.value.lightFilter,
        heaterFilter: Boolean = uiState.value.heaterFilter,
        rollerShutterFilter: Boolean = uiState.value.rollerShutterFilter,
    ) = if ((!heaterFilter && !lightFilter && !rollerShutterFilter)) {
        allDevices
    } else {
        allDevices.filter { device ->
            (heaterFilter && device is Device.Heater) ||
                    (lightFilter && device is Device.Light) ||
                    (rollerShutterFilter && device is Device.RollerShutter)
        }
    }

    fun toggleLightFilter() {
        val lightFilter = !_uiState.value.lightFilter
        _uiState.value = _uiState.value.copy(
            devices = filterAllDeviceByType(lightFilter = lightFilter),
            lightFilter = lightFilter,
        )
    }

    fun toggleHeaterFilter() {
        val heaterFilter = !_uiState.value.heaterFilter
        _uiState.value = _uiState.value.copy(
            devices = filterAllDeviceByType(heaterFilter = heaterFilter),
            heaterFilter = heaterFilter
        )
    }

    fun toggleRollerShutterFilter() {
        val rollerShutterFilter = !_uiState.value.rollerShutterFilter
        _uiState.value = _uiState.value.copy(
            devices = filterAllDeviceByType(rollerShutterFilter = rollerShutterFilter),
            rollerShutterFilter = rollerShutterFilter
        )
    }

    fun deleteDeviceAtPosition(bindingAdapterPosition: Int) {
        val deleteDeviceResult = runCatching {
            val device = _uiState.value.devices[bindingAdapterPosition]
            deleteDevice(device.id)
        }

        if (deleteDeviceResult.isSuccess) {
            allDevices = deleteDeviceResult.getOrNull() ?: emptyList()
            _uiState.value = _uiState.value.copy(devices = filterAllDeviceByType())
        } else {
            _uiState.value = _uiState.value.copy(error = deleteDeviceResult.exceptionOrNull())
        }
    }

    fun retry() {
        loadData()
    }
}