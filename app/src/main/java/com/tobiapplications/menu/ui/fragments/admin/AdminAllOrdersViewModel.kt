package com.tobiapplications.menu.ui.fragments.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.admin.DeleteFromFireStoreUseCase
import com.tobiapplications.menu.domain.admin.GetAndListenToAllDataUseCase
import com.tobiapplications.menu.domain.admin.UpdateDataUseCase
import com.tobiapplications.menu.model.admin.DeleteDataModel
import com.tobiapplications.menu.model.admin.ManageDataModel
import com.tobiapplications.menu.model.admin.UpdateDataModel
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.UserOrder
import com.tobiapplications.menu.utils.enums.OrderStatus
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
class AdminAllOrdersViewModel @Inject constructor(private val getAndListenToAllDataUseCase: GetAndListenToAllDataUseCase,
                                                  private val updateDataUseCase: UpdateDataUseCase,
                                                  private val deleteFromFireStoreUseCase: DeleteFromFireStoreUseCase) : ViewModel() {

    val orders : LiveData<List<UserOrder>?>
    val updateStatusResult : LiveData<Order?>

    init {
        orders = getAndListenToAllDataUseCase.observe().map {
            (it as? Result.Success<List<UserOrder>>)?.data
        }

        updateStatusResult = updateDataUseCase.observe().map {
            (it as? Result.Success<Order>)?.data
        }

        getAndListenToAllDataUseCase.execute(ManageDataModel(Constants.ORDER_COLLECTION, UserOrder::class.java))
    }

    fun updateStatus(newStatus: UpdateDataModel) {
        getAndListenToAllDataUseCase.removeSnapShotListener()
        updateDataUseCase.execute(newStatus)
    }

    fun deleteItem(order: Order?) {
//        order?.let { deleteFromFireStoreUseCase.execute(DeleteDataModel(Constants.ORDER_COLLECTION, it.id!!, )) }
    }

    fun listenToUpdates() {
        getAndListenToAllDataUseCase.addSnapShotListener()
    }
}
