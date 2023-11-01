package com.colledk.cmp

import android.app.Application
import android.content.Context
import android.util.Log
import domain.models.MemeViewModel
import initKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            appModule = module {
                single<Context> { this@MainApp }
                viewModel { MemeViewModel(get()) }
                single {
                    { Log.i("Startup", "Hello") }
                }
            }
        )
    }
}