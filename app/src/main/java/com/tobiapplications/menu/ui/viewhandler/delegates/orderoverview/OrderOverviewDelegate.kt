package com.tobiapplications.menu.ui.viewhandler.delegates.orderoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.order.OrderableItem
import com.tobiapplications.menu.ui.viewholder.orderoverview.OrderViewHolder
import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 * Created by tobias.hehrlein on 2019-05-28.
 */
class OrderOverviewDelegate : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is OrderableItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.viewholder_order_item, parent, false)
        )
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val orderHolder = holder as? OrderViewHolder
        val item = items[position] as? OrderableItem

        if (item != null) {
            orderHolder?.setOrder(item)
        }
    }
}