package com.tobiapplications.menu.ui.order

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.Drink
import com.tobiapplications.menu.model.Shisha
import com.tobiapplications.menu.ui.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.OrderAdapter
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.enums.OrderType
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.general.OrderUtils
import kotlinx.android.synthetic.main.fragment_order.*
import java.io.Serializable

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class OrderFragment : BaseFragment() {

    private var orderAdapter : OrderAdapter? = null
    private var recyclerLayoutManager: LinearLayoutManager? = null
    private var orderType : OrderType? = null

    companion object {
        fun newInstance(type: OrderType) : OrderFragment {
            val frag = OrderFragment()
            val bundle = Bundle()
            bundle.putSerializable(Constants.ORDER_TYPE, type)
            frag.arguments = bundle
            return frag
        }
    }

    override fun init() {
        orderType = arguments?.getSerializable(Constants.ORDER_TYPE) as OrderType

        initRecyclerViewAndAdapter()
        initViews()
    }

    private fun initRecyclerViewAndAdapter() {
        recyclerLayoutManager = LinearLayoutManager(context)
        orderAdapter = OrderAdapter()
        recyclerView.apply {
            layoutManager = recyclerLayoutManager
            adapter = orderAdapter
        }

        orderAdapter?.setItems(
            when (orderType) {
                OrderType.DRINKS -> listOf(
                    Drink("Beer", "0.5 l", 2.90),
                    Drink("Cola", "0.33 l",2.50),
                    Drink("Fanta", "0.33 l",2.50),
                    Drink("Water", "0.7 l",1.90),
                    Drink("Jägermeister", "0.2 l",1.00)
                )
                else -> listOf(
                    Shisha("Cherry", 5.00),
                    Shisha("Lemon", 5.00),
                    Shisha("Cherry-Lemon Gold", 6.00)
                )
            }
        )
    }

    private fun initViews() {
        add.onClick {
            OrderUtils.add(orderType, orderAdapter?.itemList)
            activity?.onBackPressed()
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_order
    }
}