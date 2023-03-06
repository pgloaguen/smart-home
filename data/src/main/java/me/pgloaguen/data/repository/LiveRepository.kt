package me.pgloaguen.data.repository

import me.pgloaguen.data.api.SmartHomeApi
import me.pgloaguen.data.api.dto.DataDTO
import me.pgloaguen.data.mapper.DeviceMapper
import me.pgloaguen.domain.model.Device
import me.pgloaguen.domain.model.DeviceId
import me.pgloaguen.domain.repository.Repository

class LiveRepository(private val api: SmartHomeApi, private val deviceMapper: DeviceMapper) :
    Repository {

    private var data: DataDTO? = null
    private var devices: List<Device>? = null

    override suspend fun fetchData() {
        data = api.fetchData()
        devices = data?.devices?.filterNotNull()?.let(deviceMapper::transform)?.toMutableList()
    }

    override fun hasData(): Boolean {
        return data != null
    }

    override fun getDevices(): List<Device> {
        return devices?.toList() ?: throw IllegalStateException("No data")
    }

    override fun deleteDevice(id: DeviceId): List<Device> {
        devices = devices?.filter { it.id != id }
        return devices ?: throw IllegalStateException("No data")
    }
}