package com.tobiapplications.menu.ui.fragments.main

import android.view.WindowManager
import androidx.lifecycle.Observer
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.activitys.MainActivity
import com.tobiapplications.menu.utils.extensions.consume
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import com.tobiapplications.menu.utils.extensions.replaceFragment
import com.tobiapplications.menu.utils.extensions.setVisible
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * Created by tobias.hehrlein on 2019-05-29.
 */
class MainFragment : BaseFragment() {

    private lateinit var viewModel: MainFragmentViewModel
    private var loggedIn = false

    companion object {
        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }

    override fun init() {
        initViews()
        initViewModel()
    }

    private fun initViews() {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        (activity as? MainActivity)?.showToolbar(true)

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_last_orders -> consume { replaceFragment(PreviousOrdersFragment.newInstance()) }
                R.id.navigation_new_orders -> consume { replaceFragment(NewOrderFragment.newInstance(loggedIn)) }
                R.id.navigation_profile -> consume { replaceFragment(ProfileFragment.newInstance()) }
                else -> false
            }
        }
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.loginStatus.observe(this, Observer {
            loggedIn = it
            navigation.setVisible(loggedIn)
            navigation.selectedItemId = R.id.navigation_new_orders
        })
    }

    private fun replaceFragment(fragment: BaseFragment) {
        replaceFragment(fragment, container = R.id.child_fragment_container, addToStack = false)
    }

    override fun getBackPressAction(): (() -> Unit)? {
        return { activity?.finish() }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_menu
    }
}