package com.tobiapplications.menu.ui.fragments.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.OrderOverviewAdapter
import com.tobiapplications.menu.utils.extensions.*
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.general.MenuUtils
import com.tobiapplications.menu.utils.general.OrderUtils
import kotlinx.android.synthetic.main.fragment_order_overview.*
import kotlinx.android.synthetic.main.fragment_order_overview.orderLayout
import kotlinx.android.synthetic.main.view_order_item_bottom.*
import kotlinx.android.synthetic.main.view_order_item_bottom.view.*

/**
 *  Created by tobiashehrlein on 2019-05-24
 */
class OrderOverviewFragment : BaseFragment() {

    private lateinit var viewModel: OrderOverviewViewModel
    private var bottomSheetBehavior: BottomSheetBehavior<View>? = null
    private var recyclerLayoutManager: LinearLayoutManager? = null
    private var orderOverviewAdapter: OrderOverviewAdapter? = null

    override fun init() {
        initViewModel()
        initRecyclerView()
        initViews()
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.headerAlpha.observe(this, Observer {
            collapseArrow.rotation = if (it > 0.3f) 0f else 180f
        })
    }

    private fun initRecyclerView() {
        recyclerLayoutManager = LinearLayoutManager(context)
        orderOverviewAdapter = OrderOverviewAdapter()
        recyclerView.apply {
            layoutManager = recyclerLayoutManager
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
            } else if (bottomSheetBehavior?.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        setOrder()
    }

    private fun setOrder() {
        val order = OrderUtils.getOrder()
        if (order.drinks.isEmpty() && order.shisha.isEmpty()) {
            return
        }

        postDelayed({
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }, 200)

        val list = order.drinks.plus(order.shisha)
        orderOverviewAdapter?.setItems(list)

        bottomLayout.totalPrice.text = list.sumByDouble { it.price * it.count }.formatEuro()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottomSheetBehavior = BottomSheetBehavior.from(orderLayout)

        bottomSheetBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, offset: Float) {
                viewModel.updateFilterHeadersAlpha(offset)
            }

            override fun onStateChanged(p0: View, p1: Int) {
                (parentFragment as? MainFragment)?.apply {
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
            .setPositiveButton(R.string.overview_delete_delete) { _, _ ->  deleteOrder()}
            .setNegativeButton(R.string.overview_delete_cancel, null)
            .show()
    }

    private fun deleteOrder() {
        OrderUtils.clearOrder()
        orderOverviewAdapter?.clear()
        totalPrice.text = Constants.EMPTY_STRING
        postDelayed( {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        }, 200)
    }

    fun invalidate() {
        setOrder()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_order_overview
    }
}