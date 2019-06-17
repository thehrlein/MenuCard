package com.tobiapplications.menu.ui.fragments.main

import androidx.recyclerview.widget.LinearLayoutManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.previousorders.PreviousOrder
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.PreviousOrdersAdapter
import kotlinx.android.synthetic.main.fragment_previous_orders.*

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class PreviousOrdersFragment : BaseFragment() {

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

    }

    private fun initRecyclerView() {
        previousOrdersAdapter = PreviousOrdersAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = previousOrdersAdapter
        }
    }

    private fun initViews() {
        previousOrdersAdapter?.setItems(listOf(PreviousOrder(""), PreviousOrder("")))
    }

    override fun getLayout(): Int {
        return R.layout.fragment_previous_orders
    }
}