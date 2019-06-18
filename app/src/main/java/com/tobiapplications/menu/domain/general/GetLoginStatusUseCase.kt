package com.tobiapplications.menu.domain.general

import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.utils.mvvm.UseCase
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class GetLoginStatusUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth) : UseCase<Unit, Boolean>() {

    override fun execute(param: Unit): Boolean {
        val user = firebaseAuth.currentUser
        return user != null
    }
}