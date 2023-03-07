package me.pgloaguen.domain.model

typealias DeviceId = Int

sealed class Device {
    abstract val id: DeviceId
    abstract val name: String

    enum class Mode { ON, OFF }

    data class Light(
        override val id: DeviceId,
        override val name: String,
        val intensity: Int,
        val mode: Mode
    ) : Device() {
        val minIntensity = 0
        val maxIntensity = 100
    }

    data class RollerShutter(
        override val id: DeviceId,
        override val name: String,
        val position: Int
    ) : Device() {
        val minPosition = 0
        val maxPosition = 100
    }

    data class Heater(
        override val id: DeviceId,
        override val name: String,
        val temperature: Float,
        val mode: Mode
    ) : Device() {
        val minTemperature = 7f
        val maxTemperature = 28f
        val stepTemperature = 0.5f
    }
}


