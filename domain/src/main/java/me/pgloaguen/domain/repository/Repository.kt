package me.pgloaguen.domain.repository

import me.pgloaguen.domain.model.Device

interface Repository {
    suspend fun fetchData()

    suspend fun hasData(): Boolean

    suspend fun getDevices(): List<Device>
}