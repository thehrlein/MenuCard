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
class GetAllDrinksUseCase @Inject constructor(private val firestore: FirebaseFirestore) : MediatorUseCase<Unit, List<Drink>>() {

    override fun execute(parameters: Unit) {
        firestore.collection(Constants.DRINK_COLLECTION)
                .addSnapshotListener { snapShot, exception ->
                    if (exception != null) {
                        onFailure()
                    }

                    val drinks = mutableListOf<Drink>()
                    snapShot?.let {
                        for (doc in it) {
                            drinks.add(doc.toObject(Drink::class.java))
                        }
                    }

                    onSuccess(drinks)
                }
    }

    private fun onSuccess(drinks: List<Drink>) {
        result.postValue(Result.Success(drinks))
    }

    private fun onFailure() {
        result.postValue(Result.Success(emptyList()))
    }
}