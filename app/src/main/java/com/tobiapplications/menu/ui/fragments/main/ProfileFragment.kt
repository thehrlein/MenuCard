package com.tobiapplications.menu.ui.fragments.main

import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import com.tobiapplications.menu.utils.extensions.onClick
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by tobias.hehrlein on 04.06.2019.
 */
class ProfileFragment : BaseFragment() {

    private lateinit var viewModel : ProfileViewModel

    companion object {
        fun newInstance() : ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun init() {
        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
    }

    private fun initViews() {
        logOut.onClick { viewModel.signOut() }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_profile
    }
}

