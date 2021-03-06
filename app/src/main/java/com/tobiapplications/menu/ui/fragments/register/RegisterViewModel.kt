package com.tobiapplications.menu.ui.fragments.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.authentication.RegisterUseCase
import com.tobiapplications.menu.domain.authentication.ValidateInputUseCase
import com.tobiapplications.menu.model.authentication.LoginData
import com.tobiapplications.menu.model.authentication.LoginValidationState
import com.tobiapplications.menu.model.authentication.AuthenticationResponse
import com.tobiapplications.menu.utils.enums.AuthenticationUiType
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.mvvm.Result
import com.tobiapplications.menu.utils.mvvm.SingleLiveEvent
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class RegisterViewModel @Inject constructor(private val validateInputUseCase: ValidateInputUseCase,
                                            private val registerUseCase: RegisterUseCase) : ViewModel() {

    private val validationResult = MutableLiveData<Result<LoginValidationState>>()
    val validation : LiveData<LoginValidationState?>
    val loading = SingleLiveEvent<Boolean>()
    private val registerTaskResult : MediatorLiveData<Result<AuthenticationResponse>>
    val registerResult : LiveData<AuthenticationResponse?>

    init {
        validation = validationResult.map {
            (it as? Result.Success<LoginValidationState>)?.data
        }

        registerTaskResult = registerUseCase.observe()

        registerResult = registerTaskResult.map {
            (it as? Result.Success<AuthenticationResponse>)?.data
        }

    }

    fun validateUi(value: String, type: AuthenticationUiType) {
        val loginData = if (type == AuthenticationUiType.EMAIL) LoginData(value, null) else LoginData(null, value)
        validateInputUseCase(loginData, validationResult)
    }

    fun register(email: String, password: String) {
        val registerResult = ((validateInputUseCase.executeNow(LoginData(email, password))) as? Result.Success<LoginValidationState>)?.data
        if (loginValid(registerResult)) {
            loading.value = true
            registerUseCase.execute(LoginData(email, password))
        } else {
            validateInputUseCase(LoginData(email, password), validationResult)
        }
    }

    private fun loginValid(loginResult: LoginValidationState?): Boolean {
        return loginResult != null && loginResult.emailError == null && loginResult.passwordError == null
    }
}