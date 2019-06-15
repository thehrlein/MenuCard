package com.tobiapplications.menu.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import com.tobiapplications.menu.ui.fragments.FragmentComponent
import com.tobiapplications.menu.utils.general.CoreService
import com.tobiapplications.menu.utils.mvvm.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 17.01.2019.
*/
abstract class BaseFragment : DaggerFragment(), FragmentComponent {

    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var coreService: CoreService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setToolbarTitle()
        setToolbarMenuRes(getToolbarMenuResId())
        setToolbarMenuClickListener(getToolbarMenuClickListener())
    }

    private fun setToolbarTitle() {
        if (canModifyAppComponents()) {
            coreService.setTitle(getToolbarTitle())
        }
    }

    fun setToolbarMenuRes(@MenuRes menuRes : Int) {
        if (canModifyAppComponents()) {
            coreService.setToolbarMenu(menuRes)
        }
    }

    fun setToolbarMenuClickListener(toolbarMenuClickListener: Toolbar.OnMenuItemClickListener?) {
        if (canModifyAppComponents()) {
            coreService.setToolbarMenuListener(toolbarMenuClickListener)
        }
    }

    abstract fun init()
    abstract fun getLayout(): Int
    open fun canModifyAppComponents() : Boolean {
        return false
    }
}

