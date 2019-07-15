package com.tobiapplications.menu.ui.fragments.main

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.adapter.PreviousOrdersAdapter
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import kotlinx.android.synthetic.main.fragment_previous_orders.*

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class PreviousOrdersFragment : BaseFragment() {

    private lateinit var viewModel: PreviousOrdersViewModel
    private var previousOrdersAdapter: PreviousOrdersAdapter? = null

    companion object {
        fun newInstance() : PreviousOrdersFragment {
            return PreviousOrdersFragment()
        }
    }

    override fun init() {
        initViewModel()
        initRecyclerView()
        initViews()
    }

    fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.prevOrders.observe(this, Observer { previousOrdersAdapter?.setItems(it?.orders?.values?.toMutableList(), true) })
    }

    private fun initRecyclerView() {
        previousOrdersAdapter = PreviousOrdersAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = previousOrdersAdapter
        }
    }

    private fun initViews() {

    }

    override fun getLayout(): Int {
        return R.layout.fragment_previous_orders
    }
}