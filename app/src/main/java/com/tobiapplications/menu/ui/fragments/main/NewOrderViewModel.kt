package com.tobiapplications.menu.ui.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.admin.AddToFireStoreUseCase
import com.tobiapplications.menu.model.admin.AddToFireStoreModel
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-15
 */
class NewOrderViewModel @Inject constructor(private val addToFireStoreUseCase: AddToFireStoreUseCase) : ViewModel() {

    val addOrderResult : LiveData<Boolean>

    init {
        addOrderResult = addToFireStoreUseCase.observe().map {
            (it as? Result.Success<Boolean>)?.data.orFalse()
        }
    }

    fun sendOrder(order: Order) {
        addToFireStoreUseCase.execute(AddToFireStoreModel(Constants.ORDER_COLLECTION, order))
    }
}