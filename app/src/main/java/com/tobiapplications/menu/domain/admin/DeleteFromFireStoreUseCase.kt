package com.tobiapplications.menu.domain.admin

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.tobiapplications.menu.model.admin.DeleteDataModel
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class DeleteFromFireStoreUseCase @Inject constructor(private val firestore: FirebaseFirestore) : MediatorUseCase<DeleteDataModel, Boolean>() {

    override fun execute(parameters: DeleteDataModel) {
        if (parameters.document == null) {
            firestore
                .collection(parameters.collection)
                .document(parameters.id)
                .delete()
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { onFailure() }
        } else {
            firestore
                .collection(parameters.collection)
                .document(parameters.document)
                .get()
                .addOnSuccessListener {
                    firestore
                        .collection(parameters.collection)
                        .document(parameters.document)
                        .update(parameters.id, FieldValue.delete())
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { onFailure() }
                }
                .addOnFailureListener { onFailure() }
        }
    }

    private fun onSuccess() {
        result.postValue(Result.Success(true))
    }

    private fun onFailure() {
        result.postValue(Result.Success(false))
    }
}