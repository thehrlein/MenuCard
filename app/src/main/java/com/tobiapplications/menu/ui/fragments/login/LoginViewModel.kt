package com.tobiapplications.menu.ui.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.authentication.ResetPasswordUseCase
import com.tobiapplications.menu.domain.authentication.SignInUseCase
import com.tobiapplications.menu.domain.authentication.SafeFireStoreUserUseCase
import com.tobiapplications.menu.domain.authentication.ValidateInputUseCase
import com.tobiapplications.menu.model.authentication.*
import com.tobiapplications.menu.utils.enums.AuthenticationUiType
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.general.AuthenticationHelper
import com.tobiapplications.menu.utils.mvvm.Result
import com.tobiapplications.menu.utils.mvvm.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val validateInputUseCase: ValidateInputUseCase,
                                         private val signInUseCase: SignInUseCase,
                                         private val resetPasswordUseCase: ResetPasswordUseCase,
                                         private val authenticationHelper: AuthenticationHelper,
                                         private val safeFireStoreUserUseCase: SafeFireStoreUserUseCase) : ViewModel() {

    private val validationResult = MutableLiveData<Result<LoginValidationState>>()
    val validation : LiveData<LoginValidationState?>
    val loading = SingleLiveEvent<Boolean>()
    private val loginTaskResult : MediatorLiveData<Result<AuthenticationResponse>>
    val loginResult : LiveData<AuthenticationResponse?>
    val resetPasswordResult : LiveData<ResetPasswordResponse?>
    val safeFireStoreUserResult : LiveData<User?>

    init {
        validation = validationResult.map {
            (it as? Result.Success<LoginValidationState>)?.data
        }

        loginTaskResult = signInUseCase.observe()

        loginResult = loginTaskResult.map {
            (it as? Result.Success<AuthenticationResponse>)?.data
        }

        resetPasswordResult = resetPasswordUseCase.observe().map {
            (it as? Result.Success<ResetPasswordResponse>)?.data
        }

        safeFireStoreUserResult = safeFireStoreUserUseCase.observe().map {
            (it as? Result.Success<User>)?.data
        }
    }

    fun validateUi(value: String, type: AuthenticationUiType) {
        val loginData = if (type == AuthenticationUiType.EMAIL) LoginData(value, null) else LoginData(null, value)
        validateInputUseCase(loginData, validationResult)
    }

    fun login(email: String, password: String) {
        val loginResult = ((validateInputUseCase.executeNow(LoginData(email, password))) as? Result.Success<LoginValidationState>)?.data
        if (loginValid(loginResult)) {
            loading.value = true
            signInUseCase.execute(LoginData(email, password))
        } else {
            validateInputUseCase(LoginData(email, password), validationResult)
        }
    }

    private fun loginValid(loginResult: LoginValidationState?): Boolean {
        return loginResult != null && loginResult.emailError == null && loginResult.passwordError == null
    }

    fun sendResetPassword(email: String) {
        loading.value = true
        resetPasswordUseCase.execute(email)
    }

    fun safeUserLocalAndInFireStore(user: User) {
        authenticationHelper.addEmailToDictionary(user.email)
        safeFireStoreUserUseCase.execute(user)
    }
}