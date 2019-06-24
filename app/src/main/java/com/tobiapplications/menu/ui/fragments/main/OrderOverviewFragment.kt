package com.tobiapplications.menu.ui.fragments.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.orderoverview.OrderOverviewItem
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.adapter.OrderOverviewAdapter
import com.tobiapplications.menu.utils.extensions.*
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.general.DisplayableItem
import com.tobiapplications.menu.utils.general.MenuUtils
import com.tobiapplications.menu.utils.general.OrderUtils
import kotlinx.android.synthetic.main.fragment_order_overview.*
import kotlinx.android.synthetic.main.fragment_order_overview.orderLayout
import kotlinx.android.synthetic.main.viewholder_order_overview_bottom.*
import kotlinx.android.synthetic.main.viewholder_order_overview_bottom.view.*

/**
 *  Created by tobiashehrlein on 2019-05-24
 */
class OrderOverviewFragment : BaseFragment() {

    private lateinit var viewModel: OrderOverviewViewModel
    private var bottomSheetBehavior: BottomSheetBehavior<View>? = null
    private var orderOverviewAdapter: OrderOverviewAdapter? = null

    override fun init() {
        initViewModel()
        initRecyclerView()
        initViews()
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.headerAlpha.observe(this, Observer { collapseArrow.rotation = if (it > 0.3f) 0f else 180f })
    }

    private fun initRecyclerView() {
        val onSendClicked : () -> Unit = {
            hideOrder()
            (parentFragment as? NewOrderFragment)?.sendOrder(OrderUtils.getOrder())
        }

        orderOverviewAdapter = OrderOverviewAdapter(onSendClicked)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = orderOverviewAdapter
        }
    }

    private fun initViews() {
        clearOrder.onClick { showDeleteOrderDialog() }
        collapseArrow.setOnClickListener {
            if (it.rotation == 180f) {
                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior?.peekHeight = MenuUtils.pxFromDp(requireContext(), 48f).toInt()
                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        orderLayout.onClick{
            if (bottomSheetBehavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
            } else if (bottomSheetBehavior?.state == BottomSheetBehavior.STATE_COLLAPSED || bottomSheetBehavior?.state == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        setOrder()
    }

    private fun hideOrder() {
        postDelayed({ bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN }, 200)
    }

    private fun setOrder() {
        val order = OrderUtils.getOrder()

        if (order.drinks.isEmpty() && order.shisha.isEmpty()) {
            return
        }

        postDelayed({ bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED }, 200)

        val list : List<DisplayableItem> = order.drinks.plus(order.shisha)
        val sumDrinks = order.drinks.sumByDouble { it.price * it.count }
        val sumShisha = order.shisha.sumByDouble { it.price * it.count }


        orderOverviewAdapter?.setItems(list)
        orderOverviewAdapter?.addItem(OrderOverviewItem((sumDrinks + sumShisha).formatEuro()))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        (parentFragment as? NewOrderFragment)?.setActionListener { it.invoke(this) }
        bottomSheetBehavior = BottomSheetBehavior.from(orderLayout)

        bottomSheetBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, offset: Float) {
                viewModel.updateFilterHeadersAlpha(offset)
            }

            override fun onStateChanged(p0: View, p1: Int) {
                (parentFragment as? NewOrderFragment)?.apply {
                    when (p1) {
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> moveFabMenuToHalfScreen()
                        BottomSheetBehavior.STATE_HIDDEN -> moveFabMenuDownToInitialPosition()
                        BottomSheetBehavior.STATE_COLLAPSED -> moveFabMenuAboveCollapseBottomSheet()
                        else -> Unit
                    }
                }
            }
        })
    }

    private fun showDeleteOrderDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.overview_delete_order))
            .setCancelable(false)
            .setPositiveButton(R.string.general_delete) { _, _ ->  deleteOrder()}
            .setNegativeButton(R.string.general_cancel, null)
            .show()
    }

    fun deleteOrder() {
        OrderUtils.clearOrder()
        orderOverviewAdapter?.clear()
        totalPrice.text = Constants.EMPTY_STRING
        hideOrder()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_order_overview
    }

    fun openOverview() {
        postDelayed({ bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED }, 200)
    }
}