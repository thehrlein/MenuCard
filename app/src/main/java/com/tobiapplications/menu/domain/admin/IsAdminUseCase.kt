package com.tobiapplications.menu.domain.admin

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.tobiapplications.menu.model.authentication.User
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class IsAdminUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<String, Boolean>() {

    override fun execute(parameters: String) {
        fireStore.collection(Constants.USER_COLLECTION)
                .whereEqualTo("email", parameters)
                .get()
                .addOnSuccessListener { onSuccess(it) }
                .addOnFailureListener { onFailure() }
    }

    private fun onSuccess(snapShot: QuerySnapshot?) {
        val user = snapShot?.firstOrNull()?.toObject(User::class.java)
        result.postValue(Result.Success(user?.admin.orFalse()))
    }

    private fun onFailure() {
        result.postValue(Result.Success(false))
    }
}