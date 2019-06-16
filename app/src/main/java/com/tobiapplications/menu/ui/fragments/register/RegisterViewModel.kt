package com.tobiapplications.menu.ui.fragments.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.tobiapplications.menu.domain.authentication.RegisterUseCase
import com.tobiapplications.menu.domain.authentication.ValidateInputUseCase
import com.tobiapplications.menu.model.authentication.LoginData
import com.tobiapplications.menu.model.authentication.LoginDataState
import com.tobiapplications.menu.model.authentication.RegisterResponse
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

    private val validationResult = MutableLiveData<Result<LoginDataState>>()
    val validation : LiveData<LoginDataState?>
    val loading = SingleLiveEvent<Boolean>()
    private val registerTaskResult : MediatorLiveData<Result<RegisterResponse>>
    val registerResult : LiveData<RegisterResponse?>

    init {
        validation = validationResult.map {
            (it as? Result.Success<LoginDataState>)?.data
        }

        registerTaskResult = registerUseCase.observe()

        registerResult = registerTaskResult.map {
            (it as? Result.Success<RegisterResponse>)?.data
        }

    }

    fun validateUi(value: String, type: AuthenticationUiType) {
        val loginData = if (type == AuthenticationUiType.EMAIL) LoginData(value, null) else LoginData(null, value)
        validateInputUseCase(loginData, validationResult)
    }

    fun register(email: String, password: String) {
        val registerResult = ((validateInputUseCase.executeNow(LoginData(email, password))) as? Result.Success<LoginDataState>)?.data
        if (loginValid(registerResult)) {
            loading.value = true
            registerUseCase.execute(LoginData(email, password))
        } else {
            validateInputUseCase(LoginData(email, password), validationResult)
        }
    }

    private fun loginValid(loginResult: LoginDataState?): Boolean {
        return loginResult != null && loginResult.emailError == null && loginResult.passwordError == null
    }
}