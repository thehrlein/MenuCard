package com.tobiapplications.menu.di.module

import com.tobiapplications.menu.ui.fragments.loadingscreen.LoadingScreenFragment
import com.tobiapplications.menu.ui.fragments.main.MainActivity
import com.tobiapplications.menu.ui.fragments.main.MainFragment
import com.tobiapplications.menu.ui.fragments.main.OrderOverviewFragment
import com.tobiapplications.menu.ui.fragments.menu.MenuFragment
import com.tobiapplications.menu.ui.fragments.addtoorder.AddToOrderFragment
import com.tobiapplications.menu.ui.fragments.login.LoginFragment
import com.tobiapplications.menu.ui.fragments.main.PreviousOrdersFragment
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
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun bindMenuFragment(): MenuFragment

    @ContributesAndroidInjector
    abstract fun bindMainFragment() : MainFragment

    @ContributesAndroidInjector
    abstract fun bindOrderFragment(): AddToOrderFragment

    @ContributesAndroidInjector
    abstract fun bindOrderOverviewFragment(): OrderOverviewFragment

    @ContributesAndroidInjector
    abstract fun bindPreviousOrdersFragment(): PreviousOrdersFragment
}

