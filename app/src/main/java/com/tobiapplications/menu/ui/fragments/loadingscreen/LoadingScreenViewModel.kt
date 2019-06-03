package com.tobiapplications.menu.ui.fragments.loadingscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.GetLoginStatusUseCase
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class LoadingScreenViewModel @Inject constructor(getLoginStatusUseCase: GetLoginStatusUseCase) : ViewModel() {

    private val loggedInStateResult = MediatorLiveData<com.tobiapplications.menu.utils.mvvm.Result<Boolean>>()
    val loggedInState : LiveData<Boolean>

    init {
        loggedInState = loggedInStateResult.map {
            (it as? com.tobiapplications.menu.utils.mvvm.Result.Success<Boolean>)?.data.orFalse()
        }

        getLoginStatusUseCase(Unit, loggedInStateResult)
    }
}