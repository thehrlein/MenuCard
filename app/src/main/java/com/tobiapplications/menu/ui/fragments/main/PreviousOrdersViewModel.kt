package com.tobiapplications.menu.ui.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.domain.admin.GetAndListenToAllOrdersUseCase
import com.tobiapplications.menu.model.admin.ManageDataModel
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-21.
 */
class PreviousOrdersViewModel @Inject constructor(getAndListenToAllOrdersUseCase: GetAndListenToAllOrdersUseCase,
                                                  firebaseAuth: FirebaseAuth) : ViewModel() {

    val prevOrders : LiveData<List<Order>?>

    init {
        prevOrders = getAndListenToAllOrdersUseCase.observe().map {
            (it as? Result.Success<List<Order>>)?.data
        }

        firebaseAuth.currentUser?.email?.let {
            getAndListenToAllOrdersUseCase.execute(ManageDataModel(Constants.ORDER_COLLECTION, it))
        }
    }
}