package com.tobiapplications.menu.domain.admin

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.tobiapplications.menu.model.admin.UpdateDataModel
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.UserOrder
import com.tobiapplications.menu.utils.enums.OrderStatus
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-25
 */
class UpdateDataUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<UpdateDataModel, Order?>() {

    override fun execute(parameters: UpdateDataModel) {
//        fireStore
//            .collection(parameters.collection)
//            .document(parameters.document)
//            .get()
//            .addOnSuccessListener {
//                val userOders = it.toObject(UserOrder::class.java)
//                val order = userOders?.orders?.firstOrNull { it.timeStamp == parameters.timeStamp } ?: return@addOnSuccessListener
//                val updatedOrder = Order(order.drinks, order.shisha, order.timeStamp, parameters.newValue as OrderStatus)
//                updatedOrder.id = order.id
//                fireStore
//                    .collection(parameters.collection)
//                    .document(parameters.document)
//                    .update(Constants.ORDERS_FIELD, FieldValue.arrayRemove(order))
//                    .addOnSuccessListener {
//                        fireStore
//                            .collection(parameters.collection)
//                            .document(parameters.document)
//                            .update(Constants.ORDERS_FIELD, FieldValue.arrayUnion(updatedOrder))
//                            .addOnSuccessListener { onSuccess(updatedOrder) }
//                            .addOnFailureListener { onFailure() }
//                    }
//                    .addOnFailureListener { onFailure() }
//            }
//            .addOnFailureListener { onFailure() }

        fireStore
            .collection(parameters.collection)
            .document(parameters.document)
            .set(parameters.newValue, SetOptions.merge())
            .addOnSuccessListener { onSuccess(Order()) }
            .addOnFailureListener { onFailure() }
    }


    private fun onSuccess(newOrder: Order) {
        result.postValue(Result.Success(newOrder))
    }

    private fun onFailure() {
        result.postValue(Result.Success(null))
    }
}