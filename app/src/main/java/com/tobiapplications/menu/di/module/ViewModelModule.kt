package com.tobiapplications.menu.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tobiapplications.menu.di.scopes.ViewModelKey
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
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainActivityViewModel::class)
//    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel) : ViewModel
//
    @Binds
    @IntoMap
    @ViewModelKey(OrderOverviewViewModel::class)
    abstract fun bindLoginViewModel(viewModel: OrderOverviewViewModel) : ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MySiteViewModel::class)
//    abstract fun bindMySiteViewModel(viewModel: MySiteViewModel) : ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(FbFeedViewModel::class)
//    abstract fun bindFbFeedViewModel(viewModel: FbFeedViewModel) : ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(FbProfileViewModel::class)
//    abstract fun bindFbProfileViewModel(viewModel: FbProfileViewModel) : ViewModel
}

