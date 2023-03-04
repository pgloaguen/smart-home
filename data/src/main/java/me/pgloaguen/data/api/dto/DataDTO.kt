package me.pgloaguen.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataDTO(

    @SerialName(value = "devices")
    val devices: List<DevicesItemDTO?>? = null,

    @SerialName(value = "user")
    val user: UserDTO? = null
)

@Serializable
data class UserDTO(

    @SerialName(value = "firstName")
    val firstName: String? = null,

    @SerialName(value = "lastName")
    val lastName: String? = null,

    @SerialName(value = "address")
    val address: AddressDTO? = null,

    @SerialName(value = "birthDate")
    val birthDate: Long? = null
)

@Serializable
data class DevicesItemDTO(

    @SerialName(value = "mode")
    val mode: String? = null,

    @SerialName(value = "temperature")
    val temperature: Int? = null,

    @SerialName(value = "id")
    val id: Int? = null,

    @SerialName(value = "deviceName")
    val deviceName: String? = null,

    @SerialName(value = "productType")
    val productType: String? = null,

    @SerialName(value = "position")
    val position: Int? = null,

    @SerialName(value = "intensity")
    val intensity: Int? = null
)

@Serializable
data class AddressDTO(

    @SerialName(value = "country")
    val country: String? = null,

    @SerialName(value = "city")
    val city: String? = null,

    @SerialName(value = "street")
    val street: String? = null,

    @SerialName(value = "postalCode")
    val postalCode: Int? = null,

    @SerialName(value = "streetCode")
    val streetCode: String? = null
)
