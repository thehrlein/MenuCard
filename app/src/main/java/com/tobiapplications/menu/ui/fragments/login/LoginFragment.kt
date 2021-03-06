package com.tobiapplications.menu.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.authentication.AuthenticationResponse
import com.tobiapplications.menu.model.authentication.LoginValidationState
import com.tobiapplications.menu.model.authentication.ResetPasswordResponse
import com.tobiapplications.menu.model.authentication.User
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.activitys.MainActivity
import com.tobiapplications.menu.ui.fragments.admin.AdminManageItemsOverviewFragment
import com.tobiapplications.menu.ui.fragments.admin.AdminStartPageFragment
import com.tobiapplications.menu.ui.fragments.main.MainFragment
import com.tobiapplications.menu.ui.fragments.register.RegisterFragment
import com.tobiapplications.menu.ui.views.general.LoadingStateDialog
import com.tobiapplications.menu.ui.views.general.LoadingStateDialogHolder
import com.tobiapplications.menu.utils.enums.AuthenticationUiType
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.extensions.replaceFragment
import com.tobiapplications.menu.utils.general.AuthenticationHelper
import com.tobiapplications.menu.utils.general.Constants
import kotlinx.android.synthetic.main.fragment_authentication_login.*
import kotlinx.android.synthetic.main.view_login_reset_password_input.view.*
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Tobias Hehrlein on 05.03.2018.
 */

class LoginFragment : BaseFragment(), LoadingStateDialogHolder {

    private lateinit var viewModel: LoginViewModel
    private var loadingDialog : LoadingStateDialog? = null

    @Inject
    lateinit var authenticationHelper: AuthenticationHelper

    companion object {
        fun newInstance(email: String? = null, password: String? = null) : LoginFragment {
            val bundle = Bundle().apply {
                putString(Constants.EMAIL, email)
                putString(Constants.PW, password)
            }
            return LoginFragment().apply { arguments = bundle }
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
        emailAutoComplete.initPrefilling(authenticationHelper.getEmailDictionary())

        password.addOnTextChangeListener { password.setErrorText(null) }
        password.onFocusLost { viewModel.validateUi(it, AuthenticationUiType.PASSWORD) }
        password.onEditorActionClicked { viewModel.login(emailAutoComplete.getText(), password.getText()) }

        signInButton.onClick {
            viewModel.login(emailAutoComplete.getText(), password.getText())
        }

        resetPassword.onClick { showResetEmailDialog(emailAutoComplete.getText()) }
        goToRegistration.onClick { replaceFragment(RegisterFragment.newInstance(emailAutoComplete.getText(), password.getText())) }

        arguments?.let {
            emailAutoComplete.setText(it.getString(Constants.EMAIL))
            password.setText(it.getString(Constants.PW))
        }
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.validation.observe(this, Observer { validateLoginData(it) })
        viewModel.loading.observe(this, Observer { if (it) setDialogLoadingState(getString(R.string.general_please_wait)) })
        viewModel.loginResult.observe(this, Observer { onLoginResult(it) })
        viewModel.resetPasswordResult.observe(this, Observer { onResetEmailResult(it) })
        viewModel.safeFireStoreUserResult.observe(this, Observer { onFireStoreResult(it) })
    }

    private fun onLoginResult(it: AuthenticationResponse?) {
        if (it?.task?.isSuccessful == true) {
            viewModel.safeUserLocalAndInFireStore(User(it.email))
        } else {
            tryExceptionHandling(it?.task?.exception)
        }
    }

    private fun onFireStoreResult(it: User?) {
        dismissDialog()
        if (it?.admin == true) {
            replaceFragment(AdminStartPageFragment.newInstance(), addToStack = false)
        } else {
            replaceFragment(MainFragment.newInstance(), addToStack = false)
        }
    }

    private fun tryExceptionHandling(exception: Exception?) {
        if (exception == null) {
            return
        }

        setDialogFailureState(try {
            throw exception
        } catch (e: FirebaseAuthInvalidUserException) {
            getString(R.string.login_error_user_not_exists)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            getString(R.string.login_error_bad_password)
        } catch (e: Exception) {
            getString(R.string.general_unknown_error)
        })

        dismissDialogDelayed()
    }

    private fun onResetEmailResult(it: ResetPasswordResponse?) {
        if (it == null) {
            setDialogFailureState(getString(R.string.login_forgot_password_general_error))
            return
        }

        if (it.result.isSuccessful) {
            setDialogSuccessState(getString(R.string.login_forgot_password_send_success, it.email))
            dismissDialogDelayed()
        } else {
            onResetEmailFailed(it.result, it.email)
        }
    }

    private fun onResetEmailFailed(result: Task<Void>, email: String?) {
        val exception = result.exception
        if (exception == null) {
            setDialogFailureState(getString(R.string.login_forgot_password_general_error))
            return
        }

        setDialogFailureState(try {
            throw exception
        } catch (e: FirebaseAuthInvalidUserException){
            getString(R.string.login_error_user_not_exists)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
          getString(R.string.login_validation_email_invalid)
        } catch (e: Exception) {
            getString(R.string.login_forgot_password_general_error)
        })

        dismissDialogDelayed { showResetEmailDialog(email) }

    }

    private fun validateLoginData(loginState: LoginValidationState?) {
        if (loginState != null) {
            emailAutoComplete.setErrorText(loginState.emailError)
            password.setErrorText(loginState.passwordError)
        }
    }

    private fun showResetEmailDialog(input: String?) {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.view_login_reset_password_input, null)
        view.emailInput.setText(input)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.login_forgot_password_title))
            .setMessage(getString(R.string.login_forgot_password_message))
            .setView(view)
            .setCancelable(false)
            .setPositiveButton(R.string.login_forgot_password_send) { _, _ -> }
            .setNegativeButton(R.string.login_forgot_password_cancel, null)
            .create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).onClick {
            val email = view.emailInput.getText()
            if (email.isNotEmpty()) {
                viewModel.sendResetPassword(email)
                dialog.dismiss()
            }
        }
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.login_toolbar_title)
    }

    override fun getBackPressAction(): (() -> Unit)? {
        return { activity?.finish() }
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    override fun getLayout(): Int {
        return R.layout.fragment_authentication_login
    }

    override fun getLoadingDialog(): LoadingStateDialog {
        if (loadingDialog == null) {
            loadingDialog = LoadingStateDialog(requireContext())
        }

        return loadingDialog!!
    }
}