package com.tobiapplications.menu.ui.fragments.loadingscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.domain.admin.IsAdminUseCase
import com.tobiapplications.menu.utils.enums.LoginState
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.mvvm.Result
import com.tobiapplications.menu.utils.mvvm.SingleLiveEvent
import com.tobiapplications.menu.utils.mvvm.ZippedLiveData
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class LoadingScreenViewModel @Inject constructor(firebaseAuth: FirebaseAuth, isAdminUseCase: IsAdminUseCase) : ViewModel() {

//    val loggedInState : LiveData<LoginState>
    private val isAdminResult : LiveData<Result<Boolean>>
    private val isAdmin : LiveData<Boolean>
    val loggedInState : ZippedLiveData<Boolean, Boolean, LoginState>

    init {
        val isLoggedIn = SingleLiveEvent(firebaseAuth.currentUser != null)
        val email = firebaseAuth.currentUser?.email.orEmpty()

        isAdminResult = isAdminUseCase.observe()

        isAdmin = isAdminResult.map {
            (it as? Result.Success<Boolean>)?.data.orFalse()
        }

        loggedInState = ZippedLiveData(
                isLoggedIn,
                isAdmin,
                loggedInStateReturnFunction(),
                loggedInStateControlFunction())

        isAdminUseCase.execute(email)
    }

    private fun loggedInStateControlFunction() : (Boolean?, Boolean?) -> Boolean = { loggedIn, isAdmin -> loggedIn != null && isAdmin != null }

    private fun loggedInStateReturnFunction(): (Boolean?, Boolean?) -> LoginState {
        return { loggedIn, isAdmin ->
            when {
                isAdmin == true -> LoginState.ADMIN
                loggedIn == true -> LoginState.LOGGED_IN
                else -> LoginState.LOGGED_OUT
            }
        }
    }
}