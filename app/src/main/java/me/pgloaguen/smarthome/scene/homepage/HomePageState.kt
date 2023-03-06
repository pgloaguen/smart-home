package me.pgloaguen.smarthome.scene.homepage

import me.pgloaguen.domain.model.Device

data class HomePageState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val devices: List<Device> = emptyList()
)