package com.tobiapplications.menu.ui.fragments.admin

import android.view.WindowManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.activitys.MainActivity
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.extensions.replaceFragment
import kotlinx.android.synthetic.main.fragment_admin_manage_items_overview.*

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class AdminManageItemsOverviewFragment : BaseFragment() {

    companion object {
        fun newInstance() : AdminManageItemsOverviewFragment{
            return AdminManageItemsOverviewFragment()
        }
    }

    override fun init() {
        manageDrinks.onClick { replaceFragment(ManageDrinksFragment.newInstance()) }
        manageShisha.onClick { replaceFragment(ManageTobaccoFragment.newInstance()) }
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.admin_manage_items_toolbar_title)
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    override fun isToolbarBackButtonEnabled(): Boolean {
        return true
    }

    override fun getLayout(): Int {
        return R.layout.fragment_admin_manage_items_overview
    }

}