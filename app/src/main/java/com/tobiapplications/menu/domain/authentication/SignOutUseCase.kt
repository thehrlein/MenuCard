package com.tobiapplications.menu.domain.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId
import com.tobiapplications.menu.model.authentication.User
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.UseCase
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 04.06.2019.
 */
class SignOutUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                         private val firestore: FirebaseFirestore) : UseCase<String?, Unit>() {

    override fun execute(param: String?) {
        firestore.collection(Constants.USER_COLLECTION)
            .whereEqualTo("email", param)
            .get()
            .addOnSuccessListener { query ->
                val user = query.firstOrNull()?.toObject(User::class.java) ?: return@addOnSuccessListener
                getInstanceId(user)
            }

        firebaseAuth.signOut()
    }


    private fun getInstanceId(user: User) {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@addOnCompleteListener
                }

                val token = task.result?.token
                if (token != null && user.firebaseToken.contains(token)) {
                    user.firebaseToken.remove(token)
                }

                setUser(user)
            }
    }

    private fun setUser(user: User) {
        firestore.collection(Constants.USER_COLLECTION)
            .document(user.email)
            .set(user)
    }

}
