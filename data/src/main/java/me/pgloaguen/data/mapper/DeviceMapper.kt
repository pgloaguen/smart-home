package me.pgloaguen.data.mapper

import android.util.Log
import me.pgloaguen.data.api.dto.DevicesItemDTO
import me.pgloaguen.domain.model.Device

class DeviceMapper {

    companion object {
        private const val TAG = "DeviceMapper"
    }

    internal fun transform(dataDTO: List<DevicesItemDTO>): List<Device> =
        dataDTO.mapNotNull { itemDTO ->
            runCatching {
                transformToDevice(itemDTO)
            }.onFailure { throwable ->
                Log.w(TAG, "Error while transforming $itemDTO", throwable)
            }.getOrNull()
        }

    private fun transformToDevice(itemDTO: DevicesItemDTO) =
        when (itemDTO.productType) {
            "Light" -> transformToLight(itemDTO)
            "Heater" -> transformToHeader(itemDTO)
            "RollerShutter" -> transformToRollerShutter(itemDTO)
            else -> throw IllegalStateException("Unknown product type ${itemDTO.productType}")
        }

    private fun transformToRollerShutter(item: DevicesItemDTO) =
        Device.RollerShutter(
            item.id!!,
            item.deviceName!!,
            item.position!!
        )

    private fun transformToHeader(item: DevicesItemDTO) =
        Device.Heater(
            item.id!!,
            item.deviceName!!,
            item.temperature!!.toFloat(),
            transformMode(item.mode),
        )

    private fun transformToLight(item: DevicesItemDTO) =
        Device.Light(
            item.id!!,
            item.deviceName!!,
            item.intensity!!,
            transformMode(item.mode),
        )

    private fun transformMode(mode: String?) = when (mode) {
        "ON" -> Device.Mode.ON
        "OFF" -> Device.Mode.OFF
        else -> throw IllegalStateException("Unknown mode $mode")
    }


}