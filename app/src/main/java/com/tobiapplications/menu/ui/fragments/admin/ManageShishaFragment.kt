package com.tobiapplications.menu.ui.fragments.admin

import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class ManageShishaFragment : BaseFragment() {

    private lateinit var viewModel: ManageShishaViewModel

    companion object {
        fun newInstance() : ManageShishaFragment {
            return ManageShishaFragment()
        }
    }

    override fun init() {

    }

    override fun getToolbarTitle(): String {
        return getString(R.string.admin_manage_shisha_toolbar_title)
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    override fun getLayout(): Int {
        return R.layout.fragment_manage_shisha
    }
}