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
import com.tobiapplications.menu.domain.authentication.LoginUseCase
import com.tobiapplications.menu.domain.authentication.ValidateInputUseCase
import com.tobiapplications.menu.model.login.LoginData
import com.tobiapplications.menu.model.login.LoginDataState
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.mvvm.Result
import com.tobiapplications.menu.utils.mvvm.SingleLiveEvent
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                         private val databaseReference: DatabaseReference,
                                         private val sharedPreferences: SharedPreferences,
                                         private val validateInputUseCase: ValidateInputUseCase,
                                         private val loginUseCase: LoginUseCase) : ViewModel() {



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
    val loginTask : LiveData<Task<AuthResult>?>
    val loginException : LiveData<Exception?>

    init {
        validation = validationResult.map {
            (it as? Result.Success<LoginDataState>)?.data
        }

        loginTaskResult = loginUseCase.observe()

        loginTask = loginTaskResult.map {
            (it as? Result.Success<Task<AuthResult>>)?.data
        }

        loginException = loginTaskResult.map {
            (it as? Result.Success<Task<AuthResult>>)?.data?.exception
        }

    }

    fun validateUi(email: String, password: String) {
        validateInputUseCase(LoginData(email, password), validationResult)
    }

    fun login(email: String, password: String) {
        loading.value = true
        loginUseCase.execute(LoginData(email, password))
    }


//
//    fun onStart(authenticationGroup: AuthenticationGroup?, loginData: LoginData?) {
//        authGroup = authenticationGroup
//        authGroup?.setLoginListenerFor(AuthenticationUtils.EMAIL)
//        authGroup?.setLoginListenerFor(AuthenticationUtils.PW)
//
//        loginData?.let {
//            authGroup?.setTextFor(AuthenticationUtils.EMAIL, it.email)
//            authGroup?.setTextFor(AuthenticationUtils.PW, it.password)
//        }
//    }
//
//    fun attemptLogin() {
//        val emailCorrect = authGroup?.isInputCorrectFrom(AuthenticationUtils.EMAIL) ?: false
//        val passwordCorrect = authGroup?.isInputCorrectFrom(AuthenticationUtils.PW) ?: false
//
//        if (emailCorrect && passwordCorrect) {
//            showLoading(true)
//            trySignIn()
//        }
//    }
//
//    private fun showLoading(load : Boolean) {
//        loading.value = load
//    }
//
//    private fun trySignIn() {
//        val email = getEmail()
//        loggedIn = false
//        firebaseAuth.signInWithEmailAndPassword(email, getPassword())
//                .addOnCompleteListener { checkLoginResult(it, email) }
//    }
//
//    private fun checkLoginResult(result: Task<AuthResult>, email: String) {
//        if (result.isSuccessful) {
//            if (!loggedIn) {
//                loggedIn = true
//                retrieveUserData()
//            } else {
//                showLoading(false)
//            }
//        } else {
//            showLoading(false)
//            loginException.value = LoginException(result, email)
//        }
//    }
//
//    private fun retrieveUserData() {
//        val firebaseUser = firebaseAuth.currentUser
//
//        firebaseUser?.let { user ->
//            val email = user.email
//
//            safeUserInFirebaseDatabase(user)
//
//            currentUser = User(user)
//
//            email?.let {
//                safeEmailToDictionary(it)
//            }
//            showLoading(false)
//            loginSuccessful.value = currentUser
//        }
//    }
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
//    fun onConfirmResetEmail(inputText: String) {
//        showLoading(true)
//        firebaseAuth.sendPasswordResetEmail(inputText)
//                .addOnCompleteListener { onResetCompleted(it, inputText) }
//    }
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