package com.tobiapplications.menu.ui.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.authentication.GetUserEmailUseCase
import com.tobiapplications.menu.domain.general.AddToFireStoreUseCase
import com.tobiapplications.menu.model.admin.AddToFireStoreModel
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.UserOrder
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-15
 */
class NewOrderViewModel @Inject constructor(private val addToFireStoreUseCase: AddToFireStoreUseCase,
                                            getUserEmailUseCase: GetUserEmailUseCase) : ViewModel() {

    val addOrderResult : LiveData<Boolean>
    private val userEmail = MutableLiveData<Result<String?>>()

    init {
        addOrderResult = addToFireStoreUseCase.observe().map {
            (it as? Result.Success<Boolean>)?.data.orFalse()
        }


        getUserEmailUseCase(Unit, userEmail)
    }

    fun sendOrder(order: Order) {
        val email = (userEmail.value as? Result.Success<String?>)?.data
        addToFireStoreUseCase.execute(AddToFireStoreModel(Constants.ORDER_COLLECTION, UserOrder(listOf(order)), email))
    }
}