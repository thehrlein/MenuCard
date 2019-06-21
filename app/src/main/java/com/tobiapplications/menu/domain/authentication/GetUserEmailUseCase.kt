package com.tobiapplications.menu.domain.authentication

import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.utils.mvvm.UseCase
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-21.
 */
class GetUserEmailUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth): UseCase<Unit, String?>() {

    override fun execute(param: Unit): String? {
        return firebaseAuth.currentUser?.email
    }
}