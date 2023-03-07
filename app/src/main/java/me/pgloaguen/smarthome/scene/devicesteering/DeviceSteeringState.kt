package me.pgloaguen.smarthome.scene.devicesteering

import me.pgloaguen.domain.model.Device

data class DeviceSteeringState(
   val device: Device? = null,
   val isLoading: Boolean = false,
   val error: Throwable? = null,
)