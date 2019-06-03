package com.tobiapplications.menu.di.component

import android.app.Application
import com.tobiapplications.menu.MenuApplication
import com.tobiapplications.menu.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by tobias.hehrlein on 17.01.19.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    AppModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    PersistenceModule::class,
    FirebaseModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MenuApplication)
}