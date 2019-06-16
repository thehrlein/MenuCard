package com.tobiapplications.menu.ui.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.authentication.ResetPasswordUseCase
import com.tobiapplications.menu.domain.authentication.SignInUseCase
import com.tobiapplications.menu.domain.authentication.ValidateInputUseCase
import com.tobiapplications.menu.model.authentication.AuthenticationResponse
import com.tobiapplications.menu.model.authentication.LoginData
import com.tobiapplications.menu.model.authentication.LoginDataState
import com.tobiapplications.menu.model.authentication.ResetPasswordResponse
import com.tobiapplications.menu.utils.enums.AuthenticationUiType
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.mvvm.Result
import com.tobiapplications.menu.utils.mvvm.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val validateInputUseCase: ValidateInputUseCase,
                                         private val signInUseCase: SignInUseCase,
                                         private val resetPasswordUseCase: ResetPasswordUseCase) : ViewModel() {

    private val validationResult = MutableLiveData<Result<LoginDataState>>()
    val validation : LiveData<LoginDataState?>
    val loading = SingleLiveEvent<Boolean>()
    private val loginTaskResult : MediatorLiveData<Result<AuthenticationResponse>>
    val loginResult : LiveData<AuthenticationResponse?>
    val resetPasswordResult : LiveData<ResetPasswordResponse?>

    init {
        validation = validationResult.map {
            (it as? Result.Success<LoginDataState>)?.data
        }

        loginTaskResult = signInUseCase.observe()

        loginResult = loginTaskResult.map {
            (it as? Result.Success<AuthenticationResponse>)?.data
        }

        resetPasswordResult = resetPasswordUseCase.observe().map {
            (it as? Result.Success<ResetPasswordResponse>)?.data
        }
    }

    fun validateUi(value: String, type: AuthenticationUiType) {
        val loginData = if (type == AuthenticationUiType.EMAIL) LoginData(value, null) else LoginData(null, value)
        validateInputUseCase(loginData, validationResult)
    }

    fun login(email: String, password: String) {
        val loginResult = ((validateInputUseCase.executeNow(LoginData(email, password))) as? Result.Success<LoginDataState>)?.data
        if (loginValid(loginResult)) {
            loading.value = true
            signInUseCase.execute(LoginData(email, password))
        } else {
            validateInputUseCase(LoginData(email, password), validationResult)
        }
    }

    private fun loginValid(loginResult: LoginDataState?): Boolean {
        return loginResult != null && loginResult.emailError == null && loginResult.passwordError == null
    }

    fun sendResetPassword(email: String) {
        loading.value = true
        resetPasswordUseCase.execute(email)
    }
}