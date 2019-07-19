package com.tobiapplications.menu.domain.authentication

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId
import com.tobiapplications.menu.model.authentication.User
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class SafeFireStoreUserUseCase @Inject constructor(private val firestore: FirebaseFirestore) : MediatorUseCase<User, User>() {

    override fun execute(parameters: User) {
        firestore.collection(Constants.USER_COLLECTION)
            .whereEqualTo("email", parameters.email)
            .get()
            .addOnSuccessListener { query ->
                val user = query.firstOrNull()?.toObject(User::class.java)
                if (user == null) {
                    getInstanceId(parameters)
                } else {
                    onSuccess(user)
                }
            }
    }

    private fun getInstanceId(user: User) {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    onFailure(user)
                }

                user.firebaseToken = task.result?.token
                addUser(user)
            }
    }

    private fun addUser(user: User) {
        firestore.collection(Constants.USER_COLLECTION)
            .document(user.email)
            .set(user)
            .addOnSuccessListener { onSuccess(user) }
            .addOnFailureListener { onFailure(user) }
    }

    private fun onSuccess(user: User) {
        result.postValue(Result.Success(user))
    }

    private fun onFailure(user: User) {
        result.postValue(Result.Success(user))
    }
}