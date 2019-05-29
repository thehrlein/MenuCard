package com.tobiapplications.menu.di.module

import com.tobiapplications.menu.ui.loadingscreen.LoadingScreenFragment
import com.tobiapplications.menu.ui.main.MainActivity
import com.tobiapplications.menu.ui.main.MainFragment
import com.tobiapplications.menu.ui.main.OrderOverviewFragment
import com.tobiapplications.menu.ui.menu.MenuFragment
import com.tobiapplications.menu.ui.order.OrderFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by tobias.hehrlein on 17.01.2019.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun bindLoadingScreenFragment(): LoadingScreenFragment

    @ContributesAndroidInjector
    abstract fun bindMenuFragment(): MenuFragment

    @ContributesAndroidInjector
    abstract fun bindMainFragment() : MainFragment

    @ContributesAndroidInjector
    abstract fun bindOrderFragment(): OrderFragment

    @ContributesAndroidInjector
    abstract fun bindOrderOverviewFragment(): OrderOverviewFragment
}

