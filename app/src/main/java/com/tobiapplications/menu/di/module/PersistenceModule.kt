package com.tobiapplications.menu.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.tobiapplications.menu.utils.persistence.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by tobias.hehrlein on 23.04.19.
 */
@Module
class PersistenceModule  {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(sharedPreferences: SharedPreferences): SharedPreferencesManager {
        return SharedPreferencesManager(sharedPreferences)
    }
}