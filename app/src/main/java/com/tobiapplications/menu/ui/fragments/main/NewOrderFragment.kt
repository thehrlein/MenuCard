package com.tobiapplications.menu.ui.fragments.main

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.ui.fragments.FragmentComponent
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.fragments.addtoorder.AddDrinksFragment
import com.tobiapplications.menu.ui.fragments.addtoorder.AddShishaFragment
import com.tobiapplications.menu.ui.fragments.login.LoginFragment
import com.tobiapplications.menu.ui.views.general.LoadingStateDialog
import com.tobiapplications.menu.ui.views.general.LoadingStateDialogHolder
import com.tobiapplications.menu.utils.extensions.*
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.general.OrderUtils
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.orderLayout
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class NewOrderFragment : BaseFragment(), LoadingStateDialogHolder {

    private lateinit var viewModel: NewOrderViewModel
    private var bottomSheetBehavior : BottomSheetBehavior<View>? = null
    private var loadingDialog : LoadingStateDialog? = null

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
        fab_drinks.onClick { replaceFragment(AddDrinksFragment.newInstance()) }
        fab_shisha.onClick { replaceFragment(AddShishaFragment.newInstance()) }
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.addOrderResult.observe(this, Observer { onSendOrderResult(it) })
    }

    private fun onSendOrderResult(it: Boolean?) {
        if (it == true) {
            makeKonfetti()
            setDialogSuccessState(getString(R.string.overview_send_order_success))
            (orderLayout as? OrderOverviewFragment)?.deleteOrder()
        } else {
            setDialogFailureState(getString(R.string.overview_send_order_failed))
            (orderLayout as? OrderOverviewFragment)?.openOverview()
        }

        dismissDialogDelayed()
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
        if (order.drinks.isEmpty() && order.shisha.isEmpty()) {
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

    private fun makeKonfetti() {
        konfetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.RECT, Shape.CIRCLE)
            .addSizes(Size(12, 5f))
            .setPosition(-50f, konfetti.width + 50f, -50f, -50f)
            .streamFor(300, 3000L)

    }

    override fun getLoadingDialog(): LoadingStateDialog {
        if (loadingDialog == null) {
            loadingDialog = LoadingStateDialog(requireContext())
        }

        return loadingDialog!!
    }

    fun sendOrder(order: Order) {
        setDialogLoadingState(getString(R.string.general_please_wait))
        viewModel.sendOrder(order)
    }
}