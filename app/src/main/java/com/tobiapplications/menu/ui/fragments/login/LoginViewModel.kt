package com.tobiapplications.menu.ui.fragments.login

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.tobiapplications.menu.domain.authentication.ResetPasswordUseCase
import com.tobiapplications.menu.domain.authentication.SignInUseCase
import com.tobiapplications.menu.domain.authentication.ValidateInputUseCase
import com.tobiapplications.menu.model.authentication.LoginData
import com.tobiapplications.menu.model.authentication.LoginDataState
import com.tobiapplications.menu.model.authentication.ResetPasswordResponse
import com.tobiapplications.menu.utils.enums.AuthenticationUiType
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.general.CoreService
import com.tobiapplications.menu.utils.mvvm.Result
import com.tobiapplications.menu.utils.mvvm.SingleLiveEvent
import java.lang.Exception
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                         private val databaseReference: DatabaseReference,
                                         private val sharedPreferences: SharedPreferences,
                                         private val validateInputUseCase: ValidateInputUseCase,
                                         private val signInUseCase: SignInUseCase,
                                         private val resetPasswordUseCase: ResetPasswordUseCase) : ViewModel() {



//    private var loggedIn = false
//    private var currentUser : User? = null
//    var loading = SingleLiveEvent<Boolean>()
//    val loginException = NonNullableLiveEvent<LoginException>()
//    val loginSuccessful = SingleLiveEvent<User>()
//    val resetEmailResult = SingleLiveEvent<ResetEmailResult>()

    private val validationResult = MutableLiveData<Result<LoginDataState>>()
    val validation : LiveData<LoginDataState?>
    val loading = SingleLiveEvent<Boolean>()
    private val loginTaskResult : MediatorLiveData<Result<Task<AuthResult>>>
    val loginSuccess : LiveData<Task<AuthResult>?>
    val loginException : LiveData<Exception?>
    val resetPasswordResult : LiveData<ResetPasswordResponse?>

    init {
        validation = validationResult.map {
            (it as? Result.Success<LoginDataState>)?.data
        }

        loginTaskResult = signInUseCase.observe()

        loginSuccess = loginTaskResult.map {
            (it as? Result.Success<Task<AuthResult>>)?.data
        }

        loginException = loginTaskResult.map {
            (it as? Result.Success<Task<AuthResult>>)?.data?.exception
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



//
//    private fun safeUserInFirebaseDatabase(user: FirebaseUser) {
//        databaseReference
//                .child(Constants.FIREBASE_USERS)
//                .child(AuthenticationUtils.reformatEmailForDatabase(user.email)!!)
//                .setValue(user.uid)
//    }
//
//    private fun safeEmailToDictionary(email: String) {
//        AuthenticationUtils.addEmailToDictionary(sharedPreferences, email, AuthenticationUtils.EmailListType.LOGIN)
//    }
//
//
//    private fun onResetCompleted(result: Task<Void>, email: String) {
//        showLoading(false)
//        resetEmailResult.value = ResetEmailResult(result, email)
//    }
//
//    fun getEmail(): String {
//        return getInput(AuthenticationUtils.EMAIL)
//    }
//
//    fun getPassword(): String {
//        return getInput(AuthenticationUtils.PW)
//    }
//
//    private fun getInput(key : String) = authGroup?.getTextFrom(key) ?: ""

}