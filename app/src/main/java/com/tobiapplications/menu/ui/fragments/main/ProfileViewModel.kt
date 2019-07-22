package com.tobiapplications.menu.ui.fragments.main

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.domain.authentication.SignOutUseCase
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 04.06.2019.
 */
class ProfileViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                           private val signOutUseCase: SignOutUseCase) : ViewModel() {



    init {

    }

    fun signOut() {
        signOutUseCase.invoke(firebaseAuth.currentUser?.email)
    }
}

