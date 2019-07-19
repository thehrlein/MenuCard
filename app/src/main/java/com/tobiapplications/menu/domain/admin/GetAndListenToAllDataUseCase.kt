package com.tobiapplications.menu.domain.admin

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.tobiapplications.menu.model.admin.FireStoreItem
import com.tobiapplications.menu.model.admin.ManageDataModel
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class GetAndListenToAllDataUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<ManageDataModel, List<FireStoreItem>>() {

    private var snapShotListener : ListenerRegistration? = null
    private var dataModel : ManageDataModel? = null

    override fun execute(parameters: ManageDataModel) {
        this.dataModel = parameters
        registerSnapSnotListener()
    }

    private fun registerSnapSnotListener() {
        val model = dataModel
        if (model?.clazz == null) {
            onFailure()
        } else {
             snapShotListener = fireStore.collection(model.collection)
                .addSnapshotListener { snapShot, exception ->
                    if (exception != null) {
                        onFailure()
                    }

                    val items = mutableListOf<FireStoreItem>()
                    snapShot?.let {
                        for (doc in it) {
                            val item: FireStoreItem = doc.toObject(model.clazz)
                            item.fireId = doc.id
                            items.add(item)
                        }
                    }

                    onSuccess(items)
                }
        }
    }

    private fun onSuccess(drinks: List<FireStoreItem>) {
        result.postValue(Result.Success(drinks))
    }

    private fun onFailure() {
        result.postValue(Result.Success(emptyList()))
    }
}