package com.corbellini.superheroapp

import android.app.Application
import feature.superhero.di.heroDomain
import feature.superhero.di.heroPresentation
import feature.superhero.di.heroRemoteModule
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