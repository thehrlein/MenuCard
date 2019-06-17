package com.tobiapplications.menu.domain.admin

import com.google.firebase.firestore.FirebaseFirestore
import com.tobiapplications.menu.model.admin.FireStoreItem
import com.tobiapplications.menu.model.admin.GetManageDataModel
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class GetAndListenToAllDataUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<GetManageDataModel, List<FireStoreItem>>() {

    override fun execute(parameters: GetManageDataModel) {
        fireStore.collection(parameters.collection)
                .addSnapshotListener { snapShot, exception ->
                    if (exception != null) {
                        onFailure()
                    }

                    val drinks = mutableListOf<FireStoreItem>()
                    snapShot?.let {
                        for (doc in it) {
                            val drink : FireStoreItem = doc.toObject(parameters.clazz)
                            drink.id = doc.id
                            drinks.add(drink)
                        }
                    }

                    onSuccess(drinks)
                }
    }

    private fun onSuccess(drinks: List<FireStoreItem>) {
        result.postValue(Result.Success(drinks))
    }

    private fun onFailure() {
        result.postValue(Result.Success(emptyList()))
    }
}