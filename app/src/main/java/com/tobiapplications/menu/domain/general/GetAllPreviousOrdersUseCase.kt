package com.tobiapplications.menu.domain.general

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.tobiapplications.menu.model.admin.FireStoreItem
import com.tobiapplications.menu.model.order.UserOrder
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-21.
 */
class GetAllPreviousOrdersUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<String, UserOrder>() {

    override fun execute(parameters: String) {
        fireStore.collection(Constants.ORDER_COLLECTION)
            .document(parameters)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    onFailure()
                }

                var userOrder = UserOrder(emptyMap())
                snapshot?.let {
                    userOrder = it.toObject(UserOrder::class.java) ?: UserOrder(emptyMap())
                }

                onSuccess(userOrder)
            }
    }

    private fun onSuccess(userOrder: UserOrder) {
        result.postValue(Result.Success(userOrder))
    }

    private fun onFailure() {
        result.postValue(Result.Success(UserOrder(emptyMap())))
    }
}