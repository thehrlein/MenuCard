package com.tobiapplications.menu.ui.fragments.login

import android.app.Dialog
import android.content.SharedPreferences
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.login.LoginDataState
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.fragments.main.MainActivity
import com.tobiapplications.menu.ui.views.general.LoadingStateDialog
import com.tobiapplications.menu.ui.views.general.LoadingStateDialogHolder
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.extensions.toast
import kotlinx.android.synthetic.main.fragment_authentication_login.*
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Tobias Hehrlein on 05.03.2018.
 */

class LoginFragment : BaseFragment(), LoadingStateDialogHolder {

    @Inject
    lateinit var sharedPreferences : SharedPreferences

    private lateinit var viewModel: LoginViewModel
    private var loadingDialog : LoadingStateDialog? = null
//    private var loginData : LoginData? = null

    companion object {
        fun newInstance() : LoginFragment {
            return LoginFragment()
        }
    }

    override fun init() {
        initViews()
        initViewModel()
//        initListener()
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.validation.observe(this, Observer { validateLoginData(it) })
        viewModel.loading.observe(this, Observer { if (it) setDialogLoadingState(getString(R.string.general_please_wait)) })
//        viewModel.onStart(createAuthGroup(), loginData)
//        viewModel.loading.observe(this, Observer { showLoading(it) })
        viewModel.loginException.observe(this, Observer { tryExceptionHandling(it) })
        viewModel.loginTask.observe(this, Observer { onLogins(it) })
//        viewModel.loginSuccessful.observe(this, Observer { onLoginSuccessful(it) })
//        viewModel.resetEmailResult.observe(this, Observer { onResetEmailResult(it) })
    }

    private fun onLogins(it: Task<AuthResult>?) {
        val a = it
    }

    private fun validateLoginData(loginState: LoginDataState?) {
        if (loginState != null) {

            emailAutoComplete.setErrorText(loginState.emailError)
            password.setErrorText(loginState.passwordError)

        }
    }

    private fun initViews() {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        (activity as? MainActivity)?.showToolbar(true)

        emailAutoComplete.addOnTextChangeListener { emailAutoComplete.setErrorText(null) }
        emailAutoComplete.onFocusLost { viewModel.validateUi(it, password.getText(true)) }
        emailAutoComplete.onEditorActionClicked { password.requestFocus() }

        password.addOnTextChangeListener { emailAutoComplete.setErrorText(null) }
        password.onFocusLost { viewModel.validateUi(emailAutoComplete.getText(true), it) }
        password.onEditorActionClicked { viewModel.login(emailAutoComplete.getText(), password.getText()) }

        signInButton.onClick { viewModel.login(emailAutoComplete.getText(), password.getText()) }
    }

    private fun tryExceptionHandling(exception: Exception?) {
        setDialogFailureState(try {
            throw exception ?: RuntimeException()
        } catch (e: FirebaseAuthInvalidUserException) {
           getString(R.string.login_error_user_not_exists)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
           getString(R.string.login_error_bad_password)
        } catch (e: Exception) {
            getString(R.string.login_error_unknown_error)
        })

        dismissDialogDelayed()
    }

//    private fun onResetEmailResult(resetEmailResult: ResetEmailResult?) {
//        if (resetEmailResult?.result?.isSuccessful == true) {
//            showResetEmailSendDialog(resetEmailResult.email)
//        } else {
//            onResetEmailFailed(resetEmailResult?.result, resetEmailResult?.email)
//        }
//    }
//
//    private fun onLoginSuccessful(user: User?) {
//        if (user?.verified == true) {
//            showWelcomeToast(user.name)
//            openFragment(MenuFragment.newInstance(user), false)
//        } else {
//            showAccountNotValidated()
//        }
//    }
//

//    private fun initListener() {
//        ClickUtils.clicks(signInButton, Action1 { viewModel.attemptLogin() })
//        ClickUtils.clicks(goToRegistration, Action1 {
//            val regFragment = RegistrationFragment.newInstance(viewModel.getEmail(), viewModel.getPassword())
//            regFragment.setTargetFragment(this, Constants.REQUEST_CODE)
//            openFragment(regFragment,true) })
//        ClickUtils.clicks(resetPassword, Action1 { showResetEmailDialog(viewModel.getEmail()) })
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        val email = data?.getStringExtra(AuthenticationUtils.EMAIL)
//        val pw = data?.getStringExtra(AuthenticationUtils.PW)
//
//        loginData = LoginData(email, pw)
//    }
//
//    private fun createAuthGroup(): AuthenticationGroup? {
//        val authGroup = AuthenticationGroup(this)
//        authGroup.addLoginView(emailAutoComplete, AuthenticationUtils.EMAIL)
//        authGroup.addLoginView(password, AuthenticationUtils.PW)
//        return authGroup
//    }
//
//    override fun doEditorAction() {
//        viewModel.attemptLogin()
//    }
//
//    private fun showLoading(load : Boolean) {
//        InputHandler.closeKeyboard(activity)
//        if (load) {
//            if (isDialogShowing(loadingDialog)) {
//                loadingDialog?.dismiss()
//            }
//            loadingDialog = indeterminateProgressDialog(context!!.getString(R.string.general_loading))
//            loadingDialog?.show()
//        } else {
//            loadingDialog?.dismiss()
//        }
//    }
//
    private fun showWelcomeToast(name: String?) {
        if (name != null) {
            toast("Welcome back $name")
        } else {
            toast("Welcome")
        }
    }
//
//    private fun tryExceptionHandling(loginException: LoginException) {
//        val dialog : Dialog = try {
//            throw loginException.task.exception ?: RuntimeException()
//        } catch (e: FirebaseAuthInvalidUserException) {
//            DialogBuilderUtil.createOneButtonDialog(context, "Error", getString(R.string.error_user_not_exists, loginException.email))
//        } catch (e: FirebaseAuthInvalidCredentialsException) {
//            DialogBuilderUtil.createOneButtonDialog(context, "Error", getString(R.string.error_bad_password))
//        } catch (e: Exception) {
//            DialogBuilderUtil.createOneButtonDialog(context, "Error", getString(R.string.error_unknown_error))
//        }
//
//        dialog.show()
//    }
//
//    private fun showResetEmailDialog(input: String?) {
//        val dialog = ResetEmailDialog(context!!, R.style.Theme_Transparent_Full_Width, sharedPreferences)
//        dialog.title = getString(R.string.title_reset_password)
//        dialog.emailHint = getString(R.string.hint_email)
//        dialog.prefillEmail(input!!)
//        dialog.setConfirmButton(getString(R.string.action_send), object : EditTextDialogListener {
//            override fun onConfirm(inputText: String) {
//                dialog.dismiss()
//                viewModel.onConfirmResetEmail(inputText)
//            }
//        })
//        dialog.setCancelButton(getString(R.string.action_cancel), View.OnClickListener { dialog.dismiss() })
//        dialog.show()
//    }
//
//    private fun showResetEmailSendDialog(email: String) {
//        val dialog = alert(getString(R.string.successful), getString(R.string.action_reset_password_send, email))
//        dialog.show()
//    }
//
//    private fun onResetEmailFailed(result: Task<Void>?, email: String?) {
//        val dialog : Dialog = try {
//            throw result?.exception ?: RuntimeException()
//        } catch (e: FirebaseAuthInvalidUserException) {
//            DialogBuilderUtil.createOneButtonDialog(context, getString(R.string.error_title), getString(R.string.error_user_not_exists, email),
//                    false) { showResetEmailDialog(email) }
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            DialogBuilderUtil.createOneButtonDialog(context, getString(R.string.error_title),getString(R.string.error_unknown_error),
//                    false) { showResetEmailDialog(email) }
//        }
//
//        dialog.show()
//    }
//
//    private fun showAccountNotValidated() {
//        DialogBuilderUtil.createOneButtonDialog(context, getString(R.string.alert_dialog_title_attention), getString(R.string.error_email_not_validated)).show()
//    }
//
//    private fun openFragment(fragment: Fragment, addToStack: Boolean) {
//        replaceFragment(fragment, addToStack)
//    }

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