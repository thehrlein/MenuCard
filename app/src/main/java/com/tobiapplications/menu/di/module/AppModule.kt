package com.tobiapplications.menu.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by tobias.hehrlein on 17.01.19.
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: Application) : Context {
        return application
    }
}