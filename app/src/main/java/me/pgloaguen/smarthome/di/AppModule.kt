package me.pgloaguen.smarthome.di

import me.pgloaguen.smarthome.scene.devicesteering.DeviceSteeringViewModel
import me.pgloaguen.smarthome.scene.homepage.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomePageViewModel(get(), get()) }
    viewModel { parameters -> DeviceSteeringViewModel(parameters.get(), get()) }
}