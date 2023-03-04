package me.pgloaguen.smarthome.di

import me.pgloaguen.smarthome.scene.homepage.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomePageViewModel(get()) }
}