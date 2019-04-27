package net.alexandroid.teamco.di

import net.alexandroid.teamco.ui.login.LoginViewModel
import net.alexandroid.teamco.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created on 04/27/2019 by Alexey Korolev.
 */

var appModule = module {

    viewModel { MainViewModel() }
    viewModel { LoginViewModel() }
//    single { RemoteConfig() }
//    single { GoogleTagManager(androidContext()) }
//
//    single { Gson() }
//
//    single<MainRepository> { MainRepositoryImpl(get()) }
}

