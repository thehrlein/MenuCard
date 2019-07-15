package com.tobiapplications.menu.ui.fragments.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.admin.DeleteFromFireStoreUseCase
import com.tobiapplications.menu.domain.admin.GetAndListenToAllOrdersUseCase
import com.tobiapplications.menu.domain.admin.UpdateDataUseCase
import com.tobiapplications.menu.model.admin.ManageDataModel
import com.tobiapplications.menu.model.admin.UpdateDataModel
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
                                                  private val updateDataUseCase: UpdateDataUseCase,
                                                  private val deleteFromFireStoreUseCase: DeleteFromFireStoreUseCase) : ViewModel() {

    val orders : LiveData<List<Order>?>
    val updateStatusResult : LiveData<Order?>

    init {
        orders = getAndListenToAllOrdersUseCase.observe().map {
            (it as? Result.Success<List<Order>>)?.data
        }

        updateStatusResult = updateDataUseCase.observe().map {
            (it as? Result.Success<Order>)?.data
        }

        getAndListenToAllOrdersUseCase.execute(ManageDataModel(Constants.ORDER_COLLECTION, UserOrder::class.java))
    }

    fun updateStatus(newStatus: UpdateDataModel) {
        getAndListenToAllOrdersUseCase.removeSnapShotListener()
        updateDataUseCase.execute(newStatus)
    }

    fun deleteItem(order: Order?) {
//        order?.let { deleteFromFireStoreUseCase.execute(DeleteDataModel(Constants.ORDER_COLLECTION, it.id!!, )) }
    }

    fun listenToUpdates() {
        getAndListenToAllOrdersUseCase.addSnapShotListener()
    }
}
