package com.tobiapplications.menu.ui.fragments.admin

import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.UpdateDataModel
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.UserOrder
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.delegates.admin.AdminOrderDelegate
import com.tobiapplications.menu.utils.enums.OrderStatus
import com.tobiapplications.menu.utils.extensions.*
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter
import com.tobiapplications.menu.utils.general.DisplayableItem
import com.tobiapplications.menu.utils.general.SwipeDeleteCallback
import com.tobiapplications.menu.utils.general.SwipeDeleteCallbackHolder
import kotlinx.android.synthetic.main.fragment_admin_all_orders.*

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
class AdminAllOrdersFragment : BaseFragment(), SwipeDeleteCallbackHolder {

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
        val itemTouchHelper = ItemTouchHelper(SwipeDeleteCallback(this, getDrawable(R.drawable.ic_delete_white)!!, ColorDrawable(getColor(R.color.colorRed))))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = allOrdersAdapter
        }
    }

    private fun changeOrderStatus(newStatus: UpdateDataModel) {
        viewModel.updateStatus(newStatus)
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.orders.observe(this, Observer { setOrders(it) })
        viewModel.updateStatusResult.observe(this, Observer { onUpdateStatus(it) })
    }

    private fun onUpdateStatus(update: Order?) {
        if (update == null) {
            allOrdersAdapter?.notifyDataSetChanged()
        } else {
            val item = allOrdersAdapter?.itemList
                ?.mapNotNull { it as? Order }
                ?.firstOrNull { it.timeStamp ==  update.timeStamp}

            item?.let {
                it.status = update.status
                allOrdersAdapter?.notifyItemChanged(it)
            }
        }
        viewModel.listenToUpdates()
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

    override fun onItemSwiped(adapterPosition: Int) {
        viewModel.deleteItem(allOrdersAdapter?.getItem(adapterPosition) as? Order)
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