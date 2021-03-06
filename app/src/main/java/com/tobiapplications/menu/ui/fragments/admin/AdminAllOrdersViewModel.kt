package com.tobiapplications.menu.ui.fragments.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.admin.DeleteFromFireStoreUseCase
import com.tobiapplications.menu.domain.admin.GetAndListenToAllOrdersUseCase
import com.tobiapplications.menu.domain.general.AddToFireStoreUseCase
import com.tobiapplications.menu.model.admin.AddToFireStoreModel
import com.tobiapplications.menu.model.admin.DeleteDataModel
import com.tobiapplications.menu.model.admin.ManageDataModel
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.UserOrder
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
class AdminAllOrdersViewModel @Inject constructor(private val getAndListenToAllOrdersUseCase: GetAndListenToAllOrdersUseCase,
                                                  private val addToFireStoreUseCase: AddToFireStoreUseCase,
                                                  private val deleteFromFireStoreUseCase: DeleteFromFireStoreUseCase) : ViewModel() {

    val orders : LiveData<List<Order>?>

    init {
        orders = getAndListenToAllOrdersUseCase.observe().map {
            (it as? Result.Success<List<Order>>)?.data
        }

        getAndListenToAllOrdersUseCase.execute(ManageDataModel(Constants.ORDER_COLLECTION, clazz = UserOrder::class.java))
    }

    fun updateStatus(newStatus: AddToFireStoreModel) {
        addToFireStoreUseCase.execute(newStatus)
    }

    fun deleteItem(order: Order) {
        deleteFromFireStoreUseCase.execute(DeleteDataModel(Constants.ORDER_COLLECTION, order.fireId, order.timeStamp.toString()))
    }

}
