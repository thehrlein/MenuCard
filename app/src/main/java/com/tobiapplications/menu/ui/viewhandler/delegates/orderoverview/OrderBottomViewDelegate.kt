package com.tobiapplications.menu.ui.viewhandler.delegates.orderoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.orderoverview.OrderOverviewItem
import com.tobiapplications.menu.ui.viewholder.orderoverview.OrderBottomViewHolder
import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 * Created by tobias.hehrlein on 2019-06-24.
 */
class OrderBottomViewDelegate(private val onSendClicked: () -> Unit) : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is OrderOverviewItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return OrderBottomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_order_overview_bottom, parent, false), onSendClicked)
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as? OrderBottomViewHolder
        val item = items[position] as? OrderOverviewItem

        if (item != null) {
            viewHolder?.setItem(item)
        }
    }
}