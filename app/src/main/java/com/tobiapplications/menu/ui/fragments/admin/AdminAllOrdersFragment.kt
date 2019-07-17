package com.tobiapplications.menu.ui.fragments.admin

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.AddToFireStoreModel
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.UserOrder
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.delegates.admin.AdminOrderDelegate
import com.tobiapplications.menu.utils.enums.OrderStatus
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import com.tobiapplications.menu.utils.extensions.setGone
import com.tobiapplications.menu.utils.extensions.show
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_admin_all_orders.*

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
class AdminAllOrdersFragment : BaseFragment() {

    private lateinit var viewModel: AdminAllOrdersViewModel
    private var allOrdersAdapter: BaseRecyclerViewAdapter? = null

    companion object {
        fun newInstance() : AdminAllOrdersFragment{
            return AdminAllOrdersFragment()
        }
    }

    override fun init() {
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        allOrdersAdapter = BaseRecyclerViewAdapter(listOf(AdminOrderDelegate { changeOrderStatus(it) }))

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = allOrdersAdapter
        }
    }

    private fun changeOrderStatus(newStatus: AddToFireStoreModel) {
        val order = (newStatus.value as UserOrder).orders.values.first()
        if (order.status == OrderStatus.DELETE) {
            viewModel.deleteItem(order)
        } else {
            viewModel.updateStatus(newStatus)
        }
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.orders.observe(this, Observer { setOrders(it) })
    }

    private fun setOrders(it: List<Order>?) {
        if (it == null || it.isEmpty()) {
            recyclerView.setGone()
            errorNoOrders.show()
        } else {
            allOrdersAdapter?.clear()
            allOrdersAdapter?.setItems(it)
            recyclerView.show()
            errorNoOrders.setGone()
        }
        progress.setGone()
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.admin_manage_orders_toolbar_title)
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    override fun isToolbarBackButtonEnabled(): Boolean {
        return true
    }

    override fun getLayout(): Int {
        return R.layout.fragment_admin_all_orders
    }
}