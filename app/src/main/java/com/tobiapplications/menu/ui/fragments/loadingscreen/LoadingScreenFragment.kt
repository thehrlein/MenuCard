package com.tobiapplications.menu.ui.fragments.loadingscreen

import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.fragments.login.LoginFragment
import com.tobiapplications.menu.ui.activitys.MainActivity
import com.tobiapplications.menu.ui.fragments.admin.AdminManageItemsOverviewFragment
import com.tobiapplications.menu.ui.fragments.admin.AdminStartPageFragment
import com.tobiapplications.menu.ui.fragments.main.MainFragment
import com.tobiapplications.menu.utils.enums.LoginState
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import com.tobiapplications.menu.utils.extensions.postDelayed
import com.tobiapplications.menu.utils.extensions.replaceFragment
import kotlinx.android.synthetic.main.fragment_loading_screen.*

/**
 * Created by tobias.hehrlein on 2019-05-29.
 */
class LoadingScreenFragment : BaseFragment() {

    private lateinit var viewModel: LoadingScreenViewModel

    companion object {
        fun newInstance() : LoadingScreenFragment {
            return LoadingScreenFragment()
        }

        const val LOADING_ANIMATION_TIME = 500L
    }


    override fun init() {
        initViews()
        initViewModel()

        startAnimation()
    }

    private fun initViews() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        (activity as? MainActivity)?.showToolbar(false)
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.loggedInState.observe(this, Observer {
            when (it) {
                LoginState.LOGGED_IN -> openDelayed{ openFragment(MainFragment.newInstance()) }
                LoginState.ADMIN -> { openDelayed { openFragment(AdminStartPageFragment.newInstance()) } }
                else -> openDelayed { showLogInHintDialog() }
            }
        })
    }

    private fun openDelayed(action: () -> Unit) {
        postDelayed( { action() }, LOADING_ANIMATION_TIME)
    }

    private fun showLogInHintDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.loading_not_logged_in_title))
            .setMessage(getString(R.string.loading_not_logged_in_message))
            .setCancelable(false)
            .setPositiveButton(R.string.loading_not_logged_login) { _, _ ->  openFragment(LoginFragment.newInstance()) }
            .setNegativeButton(R.string.loading_not_logged_stay_anonymous) { _, _ ->  openFragment(MainFragment.newInstance()) }
            .show()
    }

    private fun openFragment(fragment: BaseFragment) {
        replaceFragment(fragment, addToStack = false)
    }

    private fun startAnimation() {
        loadingAnimation.show()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_loading_screen
    }
}