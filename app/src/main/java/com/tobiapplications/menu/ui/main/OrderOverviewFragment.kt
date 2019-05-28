package com.tobiapplications.menu.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.base.BaseFragment
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import com.tobiapplications.menu.utils.extensions.onClick
import kotlinx.android.synthetic.main.fragment_order_overview.*

/**
 *  Created by tobiashehrlein on 2019-05-24
 */
class OrderOverviewFragment : BaseFragment() {

    private lateinit var viewModel: OrderOverviewViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun init() {
        initViewModel()

    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.headerAlpha.observe(this, Observer {
            collapseArrow.alpha = it
            collapseArrow.isClickable = it > 0f
        })
        viewModel.descriptionAlpha.observe(this, Observer {
            clearOrder.alpha = it
            clearOrder.isClickable = it > 0f
        })
        collapseArrow.setOnClickListener {
            bottomSheetBehavior.state = if (bottomSheetBehavior.skipCollapsed) BottomSheetBehavior.STATE_HIDDEN else BottomSheetBehavior.STATE_COLLAPSED
        }

        orderLayout.onClick{
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottomSheetBehavior = BottomSheetBehavior.from(orderLayout)

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, offset: Float) {
                viewModel.updateFilterHeadersAlpha(offset)
            }

            override fun onStateChanged(p0: View, p1: Int) {

            }
        })

    }

    override fun getLayout(): Int {
        return R.layout.fragment_order_overview
    }
}