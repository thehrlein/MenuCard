package com.tobiapplications.menu.ui.fragments.addtoorder

import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class AddShishaFragment : BaseFragment() {

    companion object {
        fun newInstance() : AddShishaFragment {
            return AddShishaFragment()
        }
    }

    override fun init() {

    }

    override fun getLayout(): Int {
        return R.layout.fragment_add_shisha
    }
}