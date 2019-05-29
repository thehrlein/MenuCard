package com.tobiapplications.menu.ui.main

import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.base.BaseActivity
import com.tobiapplications.menu.ui.loadingscreen.LoadingScreenFragment
import com.tobiapplications.menu.utils.extensions.replaceFragment
import com.tobiapplications.menu.utils.extensions.setVisible

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun init() {
        setSupportActionBar(toolbar)
        window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        replaceFragment(LoadingScreenFragment.newInstance(), false)
    }

    fun showToolbar(show: Boolean) {
        toolbar.setVisible(show)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }
}
