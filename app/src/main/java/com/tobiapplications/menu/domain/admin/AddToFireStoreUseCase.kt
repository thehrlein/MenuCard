package com.tobiapplications.menu.domain.admin

import com.google.firebase.firestore.FirebaseFirestore
import com.tobiapplications.menu.model.admin.AddToFireStoreModel
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class AddToFireStoreUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<AddToFireStoreModel, Boolean>() {

    override fun execute(parameters: AddToFireStoreModel) {
        fireStore.collection(parameters.collection)
                .add(parameters.value)
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