package com.tobiapplications.menu.domain.general

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.tobiapplications.menu.model.admin.AddToFireStoreModel
import com.tobiapplications.menu.model.order.UserOrder
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import java.util.*
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class AddToFireStoreUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<AddToFireStoreModel, Boolean>() {

    override fun execute(parameters: AddToFireStoreModel) {
        if (parameters.document == null) {
            fireStore.collection(parameters.collection)
                .add(parameters.value)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { onFailure() }
        } else {
            fireStore.collection(parameters.collection)
                .document(parameters.document)
                .get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        update(parameters)
                    } else {
                        add(parameters)
                    }
                }
                .addOnFailureListener { onFailure() }
        }
    }

    private fun add(parameters: AddToFireStoreModel) {
        fireStore.collection(parameters.collection)
            .document(parameters.document!!)
            .set(parameters.value)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
    }

    private fun update(parameters: AddToFireStoreModel) {
        val order = (parameters.value as UserOrder).list[0]
        fireStore.collection(parameters.collection)
            .document(parameters.document!!)
            .update("list", FieldValue.arrayUnion(order))
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
    }

    private fun onSuccess() {
        result.postValue(Result.Success(true))
    }

    private fun onFailure() {
        result.postValue(Result.Success(false))
    }


}