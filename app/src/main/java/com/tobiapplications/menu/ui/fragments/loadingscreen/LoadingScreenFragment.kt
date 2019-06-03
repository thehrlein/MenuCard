package com.tobiapplications.menu.ui.fragments.loadingscreen

import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.fragments.login.LoginFragment
import com.tobiapplications.menu.ui.fragments.main.MainActivity
import com.tobiapplications.menu.ui.fragments.menu.MenuFragment
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

        const val LOADING_ANIMATION_TIME = 1500L
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
            if (it) {
                postDelayed({ openMenu() }, LOADING_ANIMATION_TIME)
            } else {
                postDelayed({ showLogInHintDialog() }, LOADING_ANIMATION_TIME)
            }
        })
    }

    private fun showLogInHintDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.loading_not_logged_in_title))
            .setMessage(getString(R.string.loading_not_logged_in_message))
            .setCancelable(false)
            .setPositiveButton(R.string.loading_not_logged_login) { _, _ ->  openLogin()}
            .setNegativeButton(R.string.loading_not_logged_stay_anonymous) { _, _ ->  openMenu()}
            .show()
    }

    private fun openLogin() {
        replaceFragment(LoginFragment.newInstance(), addToStack = false)
    }

    private fun openMenu() {
        replaceFragment(MenuFragment.newInstance(), addToStack = false)
    }

    private fun startAnimation() {
        loadingAnimation.show()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_loading_screen
    }
}