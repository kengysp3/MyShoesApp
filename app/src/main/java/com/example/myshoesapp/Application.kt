package com.example.myshoesapp

import  android.app.Application
import com.example.myshoesapp.di.repoMod
import com.example.myshoesapp.di.viewModelMod
import org.koin.core.context.GlobalContext.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    repoMod,
                    viewModelMod
                )
            )
        }
    }
}
