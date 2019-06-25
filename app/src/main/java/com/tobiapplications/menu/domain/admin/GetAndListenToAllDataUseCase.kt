package com.tobiapplications.menu.domain.admin

import com.google.firebase.firestore.FirebaseFirestore
import com.tobiapplications.menu.model.admin.FireStoreItem
import com.tobiapplications.menu.model.admin.ManageDataModel
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class GetAndListenToAllDataUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<ManageDataModel, List<FireStoreItem>>() {

    override fun execute(parameters: ManageDataModel) {
        fireStore.collection(parameters.collection)
                .addSnapshotListener { snapShot, exception ->
                    if (exception != null) {
                        onFailure()
                    }

                    val items = mutableListOf<FireStoreItem>()
                    snapShot?.let {
                        for (doc in it) {
                            val item : FireStoreItem = doc.toObject(parameters.clazz)
                            item.id = doc.id
                            items.add(item)
                        }
                    }

                    onSuccess(items)
                }
    }

    private fun onSuccess(drinks: List<FireStoreItem>) {
        result.postValue(Result.Success(drinks))
    }

    private fun onFailure() {
        result.postValue(Result.Success(emptyList()))
    }
}