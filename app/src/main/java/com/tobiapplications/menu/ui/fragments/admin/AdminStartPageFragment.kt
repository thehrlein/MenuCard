package com.tobiapplications.menu.ui.fragments.admin

import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.activitys.MainActivity
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.utils.extensions.consume
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.extensions.replaceFragment
import com.tobiapplications.menu.utils.extensions.toast
import kotlinx.android.synthetic.main.fragment_admin_start_page.*

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
class AdminStartPageFragment : BaseFragment() {

    companion object {
        fun newInstance() : AdminStartPageFragment {
            return AdminStartPageFragment()
        }
    }

    override fun init() {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        (activity as? MainActivity)?.showToolbar(true)

        manageItems.onClick { replaceFragment(AdminManageItemsOverviewFragment.newInstance()) }
        viewAllOrders.onClick { replaceFragment(AdminAllOrdersFragment.newInstance()) }
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.admin_overview_toolbar_title)
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    override fun getToolbarMenuResId(): Int {
        return R.menu.menu_admin
    }

    override fun getToolbarMenuClickListener(): Toolbar.OnMenuItemClickListener? {
        return Toolbar.OnMenuItemClickListener {
            if (it.itemId == R.id.menu_action_logout) {
                consume { activity?.onBackPressed() }
            }

            false
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_admin_start_page
    }

    override fun getBackPressAction(): (() -> Unit)? {
        return { (activity as? MainActivity)?.showLogOutOrQuitDialog() }
    }
}