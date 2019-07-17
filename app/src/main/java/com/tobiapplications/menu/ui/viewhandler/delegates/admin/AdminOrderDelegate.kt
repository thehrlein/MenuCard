package com.tobiapplications.menu.ui.viewhandler.delegates.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.AddToFireStoreModel
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.ui.viewholder.admin.AdminOrderViewHolder
import com.tobiapplications.menu.utils.enums.OrderStatus
import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
class AdminOrderDelegate(private val newStatus: (AddToFireStoreModel) -> Unit) : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is Order
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return AdminOrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_admin_order, parent, false), newStatus)
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as? AdminOrderViewHolder
        val item = items[position] as? Order

        if (item != null) {
            viewHolder?.setOrder(item)
        }
    }
}