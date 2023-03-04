package me.pgloaguen.domain.usecase

import me.pgloaguen.domain.model.Device
import me.pgloaguen.domain.repository.Repository

class GetDevices(private val repository: Repository) : suspend () -> List<Device> {
    override suspend fun invoke(): List<Device> {
        if (!repository.hasData()) repository.fetchData()
        return repository.getDevices()
    }
}