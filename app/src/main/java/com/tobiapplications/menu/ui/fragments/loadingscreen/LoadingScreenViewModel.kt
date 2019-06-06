package com.tobiapplications.menu.ui.fragments.loadingscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.domain.GetLoginStatusUseCase
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.mvvm.Result
import com.tobiapplications.menu.utils.mvvm.SingleLiveEvent
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class LoadingScreenViewModel @Inject constructor(firebaseAuth: FirebaseAuth) : ViewModel() {

    val loggedInState = SingleLiveEvent<Boolean>()

    init {
        loggedInState.value = firebaseAuth.currentUser != null
    }
}