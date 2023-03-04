package me.pgloaguen.domain.model

sealed class Device {
    abstract val id: Int
    abstract val name: String

    enum class Mode { ON, OFF }

    data class Light(
        override val id: Int,
        override val name: String,
        val intensity: Int,
        val mode: Mode
    ) : Device()

    data class RollerShutter(
        override val id: Int,
        override val name: String,
        val position: Int
    ) : Device()

    data class Heater(
        override val id: Int,
        override val name: String,
        val temperature: Int,
        val mode: Mode
    ) : Device()
}


