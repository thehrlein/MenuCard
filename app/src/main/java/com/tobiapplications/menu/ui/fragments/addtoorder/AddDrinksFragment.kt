package com.tobiapplications.menu.ui.fragments.addtoorder

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.order.OrderItem
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.OrderAdapter
import com.tobiapplications.menu.utils.extensions.*
import com.tobiapplications.menu.utils.general.OrderUtils
import kotlinx.android.synthetic.main.fragment_add_drinks.*

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class AddDrinksFragment : BaseFragment() {

    private lateinit var viewModel: AddDrinksViewModel
    private var orderAdapter : OrderAdapter? = null
    private var recyclerLayoutManager: LinearLayoutManager? = null

    companion object {
        fun newInstance() : AddDrinksFragment {
            return AddDrinksFragment()
        }
    }

    override fun init() {
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
    }

    private fun initViews() {
        add.onClick {
            OrderUtils.addDrinks(orderAdapter?.itemList?.filter { (it as? OrderItem)?.count.orDefault() > 0 }.orEmpty())
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
        return R.layout.fragment_add_drinks
    }
}