package com.tobiapplications.menu.domain.authentication

import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.utils.mvvm.UseCase
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 04.06.2019.
 */
class SignOutUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth) : UseCase<Unit, Unit>() {

    override fun execute(param: Unit) {
        firebaseAuth.signOut()
    }
}
