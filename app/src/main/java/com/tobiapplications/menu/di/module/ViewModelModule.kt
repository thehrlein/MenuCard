package com.tobiapplications.menu.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tobiapplications.menu.di.scopes.ViewModelKey
import com.tobiapplications.menu.ui.fragments.loadingscreen.LoadingScreenViewModel
import com.tobiapplications.menu.ui.fragments.login.LoginViewModel
import com.tobiapplications.menu.ui.fragments.main.OrderOverviewViewModel
import com.tobiapplications.menu.utils.mvvm.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by tobias.hehrlein on 17.01.2019.
*/
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderOverviewViewModel::class)
    abstract fun bindOrderOverviewViewModel(viewModel: OrderOverviewViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoadingScreenViewModel::class)
    abstract fun bindLoadingScreenViewModel(viewModel: LoadingScreenViewModel) : ViewModel

}

