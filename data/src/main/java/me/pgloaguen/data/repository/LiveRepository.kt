package me.pgloaguen.data.repository

import me.pgloaguen.data.api.SmartHomeApi
import me.pgloaguen.data.api.dto.DataDTO
import me.pgloaguen.data.mapper.DeviceMapper
import me.pgloaguen.domain.model.Device
import me.pgloaguen.domain.repository.Repository

class LiveRepository(private val api: SmartHomeApi, private val deviceMapper: DeviceMapper) :
    Repository {

    private var data: DataDTO? = null
    private var devices: List<Device>? = null

    override suspend fun fetchData() {
        data = api.fetchData()
        devices = data?.devices?.filterNotNull()?.let(deviceMapper::transform)
    }

    override suspend fun hasData(): Boolean {
        return data != null
    }

    override suspend fun getDevices(): List<Device> {
        return devices ?: throw IllegalStateException("No data")
    }
}