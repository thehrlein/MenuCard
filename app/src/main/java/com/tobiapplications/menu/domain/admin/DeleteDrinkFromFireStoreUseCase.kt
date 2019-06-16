package com.tobiapplications.menu.domain.admin

import com.google.firebase.firestore.FirebaseFirestore
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class DeleteDrinkFromFireStoreUseCase @Inject constructor(private val firestore: FirebaseFirestore) : MediatorUseCase<Drink, Boolean>() {

    override fun execute(parameters: Drink) {
        firestore.collection(Constants.DRINK_COLLECTION)
                .document(parameters.id.orEmpty())
                .delete()
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