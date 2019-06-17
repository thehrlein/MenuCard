package com.tobiapplications.menu.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tobiapplications.menu.di.scopes.ViewModelKey
import com.tobiapplications.menu.ui.activitys.MainActivityViewModel
import com.tobiapplications.menu.ui.fragments.addtoorder.AddDrinksViewModel
import com.tobiapplications.menu.ui.fragments.addtoorder.AddShishaViewModel
import com.tobiapplications.menu.ui.fragments.admin.ManageDrinksViewModel
import com.tobiapplications.menu.ui.fragments.admin.ManageTobaccoViewModel
import com.tobiapplications.menu.ui.fragments.loadingscreen.LoadingScreenViewModel
import com.tobiapplications.menu.ui.fragments.login.LoginViewModel
import com.tobiapplications.menu.ui.fragments.main.*
import com.tobiapplications.menu.ui.fragments.register.RegisterViewModel
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
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainFragmentViewModel(viewModel: MainFragmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(viewModel: RegisterViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderOverviewViewModel::class)
    abstract fun bindOrderOverviewViewModel(viewModel: OrderOverviewViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoadingScreenViewModel::class)
    abstract fun bindLoadingScreenViewModel(viewModel: LoadingScreenViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewOrderViewModel::class)
    abstract fun bindNewOrderViewModel(viewModel: NewOrderViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ManageDrinksViewModel::class)
    abstract fun bindManageDrinksViewModel(viewModel: ManageDrinksViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ManageTobaccoViewModel::class)
    abstract fun bindManageShishaViewModel(viewModel: ManageTobaccoViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddDrinksViewModel::class)
    abstract fun bindAddDrinksViewModel(viewModel: AddDrinksViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddShishaViewModel::class)
    abstract fun bindAddShishaViewModel(viewModel: AddShishaViewModel) : ViewModel

}

