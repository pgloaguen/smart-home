package me.pgloaguen.domain.repository

import me.pgloaguen.domain.model.Device
import me.pgloaguen.domain.model.DeviceId

interface Repository {
    suspend fun fetchData()

    fun hasData(): Boolean

    fun getDevices(): List<Device>
    fun deleteDevice(id: DeviceId): List<Device>
}