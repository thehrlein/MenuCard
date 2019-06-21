package com.tobiapplications.menu.ui.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.domain.authentication.GetUserEmailUseCase
import com.tobiapplications.menu.domain.general.GetAllPreviousOrdersUseCase
import com.tobiapplications.menu.model.order.UserOrder
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-21.
 */
class PreviousOrdersViewModel @Inject constructor(getAllPreviousOrdersUseCase: GetAllPreviousOrdersUseCase,
                                                  firebaseAuth: FirebaseAuth) : ViewModel() {

    val prevOrders : LiveData<UserOrder?>

    init {
        prevOrders = getAllPreviousOrdersUseCase.observe().map {
            (it as? Result.Success<UserOrder>)?.data
        }

        firebaseAuth.currentUser?.email?.let {
            getAllPreviousOrdersUseCase.execute(it)
        }
    }
}