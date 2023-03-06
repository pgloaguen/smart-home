package me.pgloaguen.smarthome.di

import me.pgloaguen.domain.usecase.DeleteDevice
import me.pgloaguen.domain.usecase.GetDevices
import org.koin.dsl.module

val domainModule = module {
    single { GetDevices(get()) }
    single { DeleteDevice(get()) }
}