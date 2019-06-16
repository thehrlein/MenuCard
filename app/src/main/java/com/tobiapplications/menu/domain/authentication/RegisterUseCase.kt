package com.tobiapplications.menu.domain.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.model.authentication.LoginData
import com.tobiapplications.menu.model.authentication.AuthenticationResponse
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class RegisterUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth) : MediatorUseCase<LoginData, AuthenticationResponse>() {

    override fun execute(parameters: LoginData) {
        if (parameters.email == null || parameters.password == null) {
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(parameters.email, parameters.password)
            .addOnCompleteListener { onResult(it, parameters.email) }
    }

    private fun onResult(task: Task<AuthResult>, email: String) {
        result.postValue(Result.Success(AuthenticationResponse(task, email)))
    }
}