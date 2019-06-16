package com.tobiapplications.menu.ui.fragments.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.FragmentComponent
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.fragments.addtoorder.AddToOrderFragment
import com.tobiapplications.menu.ui.fragments.login.LoginFragment
import com.tobiapplications.menu.utils.enums.OrderType
import com.tobiapplications.menu.utils.extensions.*
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.general.OrderUtils
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.orderLayout

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class NewOrderFragment : BaseFragment() {

    private lateinit var viewModel: NewOrderViewModel
    private var bottomSheetBehavior : BottomSheetBehavior<View>? = null

    companion object {
        fun newInstance(loggedIn: Boolean): NewOrderFragment {
            val args = Bundle().apply { putBoolean(Constants.LOGGED_IN, loggedIn) }
            return NewOrderFragment().apply { arguments = args }
        }
    }

    override fun init() {
        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab_menu.setClosedOnTouchOutside(true)
        fab_drinks.onClick { replaceFragment(AddToOrderFragment.newInstance(OrderType.DRINKS)) }
        fab_shisha.onClick { replaceFragment(AddToOrderFragment.newInstance(OrderType.SHISHA)) }
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
    }

    private fun onMenuItemClicked(item: MenuItem) : Boolean {
        return when (item.itemId) {
            R.id.menu_action_login -> consume { replaceFragment(LoginFragment.newInstance(), addToStack = false) }
            else -> false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBottomSheetOverview()

        val loggedIn = arguments?.getBoolean(Constants.LOGGED_IN) ?: false
        setToolbarMenuRes(if (loggedIn) FragmentComponent.NO_MENU else R.menu.menu_main_anonym)
        setToolbarMenuClickListener(if (loggedIn) null else Toolbar.OnMenuItemClickListener { item ->  onMenuItemClicked(item) })
    }

    private fun setUpBottomSheetOverview() {
        bottomSheetBehavior = BottomSheetBehavior.from(view?.findViewById(R.id.orderLayout))
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN

        val order = OrderUtils.getOrder()
        if (order.orderDrinks.isEmpty() && order.shisha.isEmpty()) {
            return
        }

        (orderLayout as? OrderOverviewFragment)?.invalidate()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    fun moveFabMenuAboveCollapseBottomSheet() {
        moveFab(-getDimen(R.dimen.order_overview_title_height))
    }

    fun moveFabMenuDownToInitialPosition() {
        moveFab(0f)
    }

    fun moveFabMenuToHalfScreen() {
        moveFab(-(view?.height?.div(2.0f) ?: 0f))
    }

    private fun moveFab(pixel: Float) {
        postDelayed( {
            ViewCompat.animate(fab_menu).translationY(pixel).start()
        }, 200)
    }
}