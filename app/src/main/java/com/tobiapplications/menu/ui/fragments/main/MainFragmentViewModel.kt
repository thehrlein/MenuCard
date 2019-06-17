package com.tobiapplications.menu.ui.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.R
import com.tobiapplications.menu.domain.GetAllDrinksUseCase
import com.tobiapplications.menu.domain.GetLoginStatusUseCase
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 06.06.2019.
 */
class MainFragmentViewModel @Inject constructor(getLoginStatusUseCase: GetLoginStatusUseCase,
                                                getAllDrinksUseCase: GetAllDrinksUseCase) : ViewModel() {

    private val loginStatusResult = MutableLiveData<Result<Boolean>>()
    val loginStatus : LiveData<Boolean>

    init {
        loginStatus = loginStatusResult.map {
            (it as? Result.Success<Boolean>)?.data.orFalse()
        }

        getAllDrinksUseCase.execute(true)
        getLoginStatusUseCase(Unit, loginStatusResult)
    }
}

