package com.example.roomone.presentation

import android.app.Application
import com.example.roomone.presentation.di.moduleCateg
import com.example.roomone.presentation.di.moduleDBCategories
import com.example.roomone.presentation.di.moduleDBProducts
import com.example.roomone.presentation.di.moduleProd
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@App)

            modules(moduleDBCategories, moduleDBProducts, moduleCateg, moduleProd)

        }

    }


}