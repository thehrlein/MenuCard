package com.tobiapplications.menu.domain.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.model.authentication.ResetPasswordResponse
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-15
 */
class ResetPasswordUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth) : MediatorUseCase<String, ResetPasswordResponse>() {

    override fun execute(parameters: String) {
        firebaseAuth.sendPasswordResetEmail(parameters)
                .addOnCompleteListener { onCompleted(parameters, it) }
    }

    private fun onCompleted(email: String, response: Task<Void>) {
        result.value = Result.Success(ResetPasswordResponse(email, response))
    }
}