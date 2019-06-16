package com.tobiapplications.menu.ui.fragments.admin

import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.utils.extensions.onClick
import kotlinx.android.synthetic.main.fragment_admin_start_page.*

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class AdminStartPageFragment : BaseFragment() {

    companion object {
        fun newInstance() : AdminStartPageFragment{
            return AdminStartPageFragment()
        }
    }

    override fun init() {
        manageDrinks.onClick {  }
        manageShisha.onClick {  }
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.admin_toolbar_title)
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    override fun getLayout(): Int {
        return R.layout.fragment_admin_start_page
    }
}