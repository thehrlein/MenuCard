package com.tobiapplications.menu.ui.fragments.register

import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.authentication.LoginDataState
import com.tobiapplications.menu.model.authentication.RegisterResponse
import com.tobiapplications.menu.ui.activitys.MainActivity
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.fragments.login.LoginFragment
import com.tobiapplications.menu.ui.views.general.LoadingStateDialog
import com.tobiapplications.menu.ui.views.general.LoadingStateDialogHolder
import com.tobiapplications.menu.utils.enums.AuthenticationUiType
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.extensions.replaceFragment
import com.tobiapplications.menu.utils.general.Constants
import kotlinx.android.synthetic.main.fragment_authentication_register.*
import java.lang.Exception

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class RegisterFragment : BaseFragment(), LoadingStateDialogHolder {

    private lateinit var viewModel: RegisterViewModel
    private var loadingDialog: LoadingStateDialog? = null

    companion object {
        fun newInstance(email: String?, password: String?): RegisterFragment {
            val bundle = Bundle().apply {
                putString(Constants.EMAIL, email)
                putString(Constants.PW, password)
            }
            return RegisterFragment().apply { arguments = bundle }
        }
    }

    override fun init() {
        initViews()
        initViewModel()
    }

    private fun initViews() {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        (activity as? MainActivity)?.showToolbar(true)

        emailAutoComplete.addOnTextChangeListener { emailAutoComplete.setErrorText(null) }
        emailAutoComplete.onFocusLost { viewModel.validateUi(it, AuthenticationUiType.EMAIL) }
        emailAutoComplete.onEditorActionClicked { password.requestFocus() }

        password.addOnTextChangeListener { password.setErrorText(null) }
        password.onFocusLost { viewModel.validateUi(it, AuthenticationUiType.PASSWORD) }
        password.onEditorActionClicked { viewModel.register(emailAutoComplete.getText(), password.getText()) }

        signUpButton.onClick { viewModel.register(emailAutoComplete.getText(), password.getText()) }
        goToLogIn.onClick { replaceFragment(LoginFragment.newInstance(emailAutoComplete.getText(), password.getText()), addToStack = false) }

        arguments?.let {
            emailAutoComplete.setText(it.getString(Constants.EMAIL))
            password.setText(it.getString(Constants.PW))
        }
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.validation.observe(this, Observer { validateLoginData(it) })
        viewModel.loading.observe(this, Observer { if (it) setDialogLoadingState(getString(R.string.general_please_wait)) })
        viewModel.registerResult.observe(this, Observer { onRegisterResult(it) })
    }

    private fun onRegisterResult(it: RegisterResponse?) {
        if (it?.task?.isSuccessful == true) {
            setDialogSuccessState(getString(R.string.register_success))
            dismissDialogDelayed { replaceFragment(LoginFragment.newInstance(it.email), addToStack = false) }
        } else {
            tryExceptionHandling(it?.task?.exception)
        }
    }

    private fun tryExceptionHandling(exception: Exception?) {
        if (exception == null) {
            return
        }

        setDialogFailureState(try {
            throw exception
        } catch (e: FirebaseAuthWeakPasswordException) {
            getString(R.string.register_error_bad_password)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            getString(R.string.register_error_invalid_email)
        } catch (e: FirebaseAuthUserCollisionException) {
            getString(R.string.register_error_user_exists)
        } catch (e: Exception) {
            getString(R.string.general_unknown_error)
        })

        dismissDialogDelayed()
    }

    private fun validateLoginData(loginState: LoginDataState?) {
        if (loginState != null) {
            emailAutoComplete.setErrorText(loginState.emailError)
            password.setErrorText(loginState.passwordError)
        }
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    override fun getLayout(): Int {
        return R.layout.fragment_authentication_register
    }

    override fun getLoadingDialog(): LoadingStateDialog {
        if (loadingDialog == null) {
            loadingDialog = LoadingStateDialog(requireContext())
        }

        return loadingDialog!!
    }
}