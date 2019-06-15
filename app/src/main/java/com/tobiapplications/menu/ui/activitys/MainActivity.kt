package com.tobiapplications.menu.ui.activitys

import android.view.WindowManager
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.FragmentComponent
import com.tobiapplications.menu.ui.fragments.base.BaseActivity
import com.tobiapplications.menu.ui.fragments.loadingscreen.LoadingScreenFragment
import com.tobiapplications.menu.ui.fragments.main.MainFragment
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import com.tobiapplications.menu.utils.extensions.replaceFragment
import com.tobiapplications.menu.utils.extensions.setVisible

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun init() {
        setSupportActionBar(toolbar)
        window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        replaceFragment(LoadingScreenFragment.newInstance(), false)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.loginStateChanged.observe(this, Observer {
            if (it.hasLoggedIn) {
////                replaceFragment(Ma inFragment.newInstance(), addToStack = false)
            } else if (it.hasLoggedOut) {
                replaceFragment(MainFragment.newInstance(), addToStack = false)
            }
        })

        viewModel.title.observe(this, Observer { toolbar.title = it })
        viewModel.toolbarMenuRes.observe(this, Observer { inflateToolbarMenu(it) })
        viewModel.toolbarMenuListener.observe(this, Observer { setToolbarMenuListener(it) })
    }

    fun showToolbar(show: Boolean) {
        toolbar.setVisible(show)
    }

    private fun inflateToolbarMenu(@MenuRes menuRes: Int?) {
        toolbar.menu.clear()
        if (menuRes != null && menuRes != FragmentComponent.NO_MENU) {
            toolbar.inflateMenu(menuRes)
        }
    }

    private fun setToolbarMenuListener(listener : Toolbar.OnMenuItemClickListener?) {
        toolbar.setOnMenuItemClickListener(listener)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }
}
