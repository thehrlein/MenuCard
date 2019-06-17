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
class GetAndListenToAllDrinksUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<Unit, List<Drink>>() {

    override fun execute(parameters: Unit) {
        fireStore.collection(Constants.DRINK_COLLECTION)
                .addSnapshotListener { snapShot, exception ->
                    if (exception != null) {
                        onFailure()
                    }

                    val drinks = mutableListOf<Drink>()
                    snapShot?.let {
                        for (doc in it) {
                            val drink = doc.toObject(Drink::class.java)
                            drink.id = doc.id
                            drinks.add(drink)
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