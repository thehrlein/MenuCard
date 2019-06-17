package com.tobiapplications.menu.ui.fragments.addtoorder

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.order.OrderDrink
import com.tobiapplications.menu.model.order.OrderItem
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.OrderAdapter
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.enums.OrderType
import com.tobiapplications.menu.utils.extensions.*
import com.tobiapplications.menu.utils.general.OrderUtils
import kotlinx.android.synthetic.main.fragment_order.*

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class AddToOrderFragment : BaseFragment() {

    private lateinit var viewModel: AddToOrderViewModel
    private var orderAdapter : OrderAdapter? = null
    private var recyclerLayoutManager: LinearLayoutManager? = null
    private var orderType : OrderType? = null

    companion object {
        fun newInstance(type: OrderType) : AddToOrderFragment {
            val bundle = Bundle().apply { putSerializable(Constants.ORDER_TYPE, type) }
            return AddToOrderFragment().apply { arguments = bundle }
        }
    }

    override fun init() {
        orderType = arguments?.getSerializable(Constants.ORDER_TYPE) as OrderType

        initRecyclerViewAndAdapter()
        initViews()
        initViewModel()
    }

    private fun initRecyclerViewAndAdapter() {
        recyclerLayoutManager = LinearLayoutManager(context)
        orderAdapter = OrderAdapter()
        recyclerView.apply {
            layoutManager = recyclerLayoutManager
            adapter = orderAdapter
        }

//        orderAdapter?.setItems(
//            when (orderType) {
//                OrderType.DRINKS -> listOf(
//                        OrderDrink("Beer", "0.5 l", 2.90),
//                        OrderDrink("Cola", "0.33 l", 2.50),
//                        OrderDrink("Fanta", "0.33 l", 2.50),
//                        OrderDrink("Water", "0.7 l", 1.90),
//                        OrderDrink("Jägermeister", "0.2 l", 1.00)
//                )
//                else -> listOf(
//                        Shisha("Cherry", 5.00),
//                        Shisha("Lemon", 5.00),
//                        Shisha("Cherry-Lemon Gold", 6.00)
//                )
//            }
//        )
    }

    private fun initViews() {
        add.onClick {
            OrderUtils.add(orderType, orderAdapter?.itemList?.filter { (it as? OrderItem)?.count.orDefault() > 0 })
            activity?.onBackPressed()
        }
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.drinks.observe(this, Observer { setDrinks(it) })
    }

    private fun setDrinks(it: List<Drink>?) {
        if (it.isNullOrEmpty()) {
            errorNoDrinks.show()
        } else {
            orderAdapter?.setItems(it)
            recyclerView.show()
            add.show()
        }
        progress.setGone()
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    override fun isToolbarBackButtonEnabled(): Boolean {
        return true
    }

    override fun getLayout(): Int {
        return R.layout.fragment_order
    }
}