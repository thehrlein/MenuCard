package com.tobiapplications.menu.ui.fragments.loadingscreen

import android.view.WindowManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.fragments.main.MainActivity
import com.tobiapplications.menu.ui.fragments.menu.MenuFragment
import com.tobiapplications.menu.utils.extensions.postDelayed
import com.tobiapplications.menu.utils.extensions.replaceFragment
import kotlinx.android.synthetic.main.fragment_loading_screen.*

/**
 * Created by tobias.hehrlein on 2019-05-29.
 */
class LoadingScreenFragment : BaseFragment() {

    companion object {
        fun newInstance() : LoadingScreenFragment {
            return LoadingScreenFragment()
        }
    }


    override fun init() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        (activity as? MainActivity)?.showToolbar(false)

        startAnimation()
        postDelayed({ openMenu() }, 1500)
    }

    private fun openMenu() {
//        stopAnimation()
        replaceFragment(MenuFragment.newInstance(), addToStack = false)
    }

    private fun startAnimation() {
        loadingAnimation.show()
    }

    private fun stopAnimation() {
        loadingAnimation.hide()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_loading_screen
    }
}