package me.pgloaguen.domain.usecase

import me.pgloaguen.domain.model.Device
import me.pgloaguen.domain.model.DeviceId
import me.pgloaguen.domain.repository.Repository

class DeleteDevice(private val repository: Repository) : (DeviceId) -> List<Device> {
    override fun invoke(id: DeviceId): List<Device> {
        return repository.deleteDevice(id)
    }
}