package com.tobiapplications.menu.ui.main

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.base.BaseFragment
import com.tobiapplications.menu.ui.order.OrderFragment
import com.tobiapplications.menu.utils.enums.OrderType
import com.tobiapplications.menu.utils.extensions.getDimen
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.extensions.postDelayed
import com.tobiapplications.menu.utils.extensions.replaceFragment
import com.tobiapplications.menu.utils.general.MenuUtils
import com.tobiapplications.menu.utils.general.OrderUtils
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.orderLayout
import kotlinx.android.synthetic.main.fragment_order_overview.*

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class MainFragment : BaseFragment() {

    private var bottomSheetBehavior : BottomSheetBehavior<View>? = null

    companion object {
        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }

    override fun init() {
        fab_menu.setClosedOnTouchOutside(true)
        fab_drinks.onClick { replaceFragment(OrderFragment.newInstance(OrderType.DRINKS)) }
        fab_shisha.onClick { replaceFragment(OrderFragment.newInstance(OrderType.SHISHA)) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBottomSheetOverview()
    }

    private fun setUpBottomSheetOverview() {
        bottomSheetBehavior = BottomSheetBehavior.from(view?.findViewById(R.id.orderLayout))

        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN

        val order = OrderUtils.getOrder()
        if (order.drinks.isEmpty() && order.shisha.isEmpty()) {
            return
        }

        (orderLayout as? OrderOverviewFragment)?.invalidate()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    fun moveFabMenuUp() {
        postDelayed( {
            val pixel = getDimen(R.dimen.menu_space_default)
            ViewCompat.animate(fab_menu).translationY(-MenuUtils.pxFromDp(requireContext(), pixel)).start()
        }, 200)
    }

    fun moveFabMenuDown() {
        postDelayed( {
            ViewCompat.animate(fab_menu).translationY(MenuUtils.pxFromDp(requireContext(), 0f)).start()
        }, 200)
    }
}