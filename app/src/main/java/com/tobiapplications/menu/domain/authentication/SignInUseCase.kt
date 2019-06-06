package com.tobiapplications.menu.domain.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.model.authentication.LoginData
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class SignInUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth) : MediatorUseCase<LoginData, Task<AuthResult>>() {

    override fun execute(parameters: LoginData) {
        firebaseAuth.signInWithEmailAndPassword(parameters.email, parameters.password)
            .addOnCompleteListener { onResult(it) }
    }

    private fun onResult(task: Task<AuthResult>) {
        result.postValue(Result.Success(task))
    }
}