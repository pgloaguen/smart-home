package me.pgloaguen.smarthome.di

import me.pgloaguen.data.api.SmartHomeApi
import me.pgloaguen.data.mapper.DeviceMapper
import me.pgloaguen.data.repository.LiveRepository
import me.pgloaguen.domain.repository.Repository
import org.koin.dsl.module

val dataModule = module {
    single { SmartHomeApi("http://storage42.com/modulotest") }
    single { DeviceMapper() }
    single<Repository> { LiveRepository(get(), get()) }
}