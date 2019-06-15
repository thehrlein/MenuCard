package com.tobiapplications.menu.domain.authentication

import android.util.Patterns
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.authentication.LoginData
import com.tobiapplications.menu.model.authentication.LoginDataState
import com.tobiapplications.menu.utils.mvvm.UseCase
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class ValidateInputUseCase @Inject constructor() : UseCase<LoginData, LoginDataState>() {

    companion object {
        // General
        const val INPUT_REQUIRED = R.string.login_validation_field_required

        // Email
        const val EMAIL_INVALID = R.string.login_validation_email_invalid

        // Password
        const val PASSWORD_TOO_SHORT = R.string.login_validation_password_too_short
    }

    override fun execute(param: LoginData): LoginDataState {
        var emailError: Int? = null
        var passwordError : Int? = null
        if (param.email != null) {
            emailError = validateEmail(param.email)
        }
        if (param.password != null) {
            passwordError = validatePassword(param.password)
        }

        return LoginDataState(emailError, passwordError)
    }

    private fun validateEmail(email: String): Int? {
        return when {
            email.isEmpty() -> INPUT_REQUIRED
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> EMAIL_INVALID
            else -> null
        }
    }

    private fun validatePassword(password: String): Int? {
        return when {
            password.isEmpty() -> INPUT_REQUIRED
            password.length < 6 -> PASSWORD_TOO_SHORT
            else -> null
        }
    }
}