package com.corbellini.superheroapp

import android.app.Application
import feature.superhero.domain.heroDomain
import feature.superhero.presentation.heroPresentation
import feature.superhero.data.heroRemoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SuperHeroApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@SuperHeroApplication)
            modules(
                heroPresentation,
                heroRemoteModule,
                heroDomain,
            )
        }

    }

}