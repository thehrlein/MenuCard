package com.tobiapplications.menu.di.module

import com.tobiapplications.menu.ui.fragments.loadingscreen.LoadingScreenFragment
import com.tobiapplications.menu.ui.activitys.MainActivity
import com.tobiapplications.menu.ui.fragments.addtoorder.AddDrinksFragment
import com.tobiapplications.menu.ui.fragments.addtoorder.AddShishaFragment
import com.tobiapplications.menu.ui.fragments.addtoorder.TobaccoSelectionFragment
import com.tobiapplications.menu.ui.fragments.admin.*
import com.tobiapplications.menu.ui.fragments.login.LoginFragment
import com.tobiapplications.menu.ui.fragments.main.*
import com.tobiapplications.menu.ui.fragments.register.RegisterFragment
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
    abstract fun bindRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun bindMenuFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun bindMainFragment() : NewOrderFragment

    @ContributesAndroidInjector
    abstract fun bindAddDrinksFragment(): AddDrinksFragment

    @ContributesAndroidInjector
    abstract fun bindAddShishaFragment(): AddShishaFragment

    @ContributesAndroidInjector
    abstract fun bindOrderOverviewFragment(): OrderOverviewFragment

    @ContributesAndroidInjector
    abstract fun bindPreviousOrdersFragment(): PreviousOrdersFragment

    @ContributesAndroidInjector
    abstract fun bindProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun bindAdminStartPageFragment(): AdminStartPageFragment

    @ContributesAndroidInjector
    abstract fun bindAdminManageItemsOverviewFragment(): AdminManageItemsOverviewFragment

    @ContributesAndroidInjector
    abstract fun bindAdminAllOrdersFragment() : AdminAllOrdersFragment

    @ContributesAndroidInjector
    abstract fun bindManageDrinksFragment() : ManageDrinksFragment

    @ContributesAndroidInjector
    abstract fun bindManageShishaFragment() : ManageTobaccoFragment

    @ContributesAndroidInjector
    abstract fun bindTobaccoSelectionFragment() : TobaccoSelectionFragment
}

