package com.tobiapplications.menu.ui.fragments.menu

import android.view.WindowManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.fragments.main.MainActivity
import com.tobiapplications.menu.ui.fragments.main.MainFragment
import com.tobiapplications.menu.ui.fragments.main.PreviousOrdersFragment
import com.tobiapplications.menu.utils.extensions.consume
import com.tobiapplications.menu.utils.extensions.replaceFragment
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * Created by tobias.hehrlein on 2019-05-29.
 */
class MenuFragment : BaseFragment() {

    companion object {
        fun newInstance() : MenuFragment {
            return MenuFragment()
        }
    }

    override fun init() {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        (activity as? MainActivity)?.showToolbar(true)

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_last_orders -> consume { replaceFragment(PreviousOrdersFragment.newInstance()) }
                R.id.navigation_new_orders -> consume { replaceFragment(MainFragment.newInstance()) }
                R.id.navigation_profile -> consume { replaceFragment(MainFragment.newInstance()) }
                else -> false
            }
        }

        navigation.selectedItemId = R.id.navigation_new_orders
    }

    private fun replaceFragment(fragment: BaseFragment) {
        replaceFragment(fragment, container = R.id.child_fragment_container, addToStack = false)
    }


    override fun getLayout(): Int {
        return R.layout.fragment_menu
    }
}