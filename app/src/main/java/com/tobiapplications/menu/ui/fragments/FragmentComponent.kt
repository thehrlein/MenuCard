package com.tobiapplications.menu.ui.fragments

import androidx.appcompat.widget.Toolbar

/**
 * Created by Tobias Hehrlein on 19.03.2018.
 */

interface FragmentComponent {

    companion object {
        const val NO_MENU = -1
    }

    fun getToolbarTitle() : String {
        return "MenuCard"
    }

    fun isToolbarBackButtonEnabled() : Boolean {
        return false
    }

    fun getToolbarMenuResId() : Int {
        return NO_MENU
    }

    fun getToolbarMenuClickListener() : Toolbar.OnMenuItemClickListener? {
        return null
    }
}