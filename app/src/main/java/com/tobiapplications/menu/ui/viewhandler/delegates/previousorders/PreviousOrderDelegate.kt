package com.tobiapplications.menu.ui.viewhandler.delegates.previousorders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.previousorders.PreviousOrder
import com.tobiapplications.menu.ui.viewholder.previousorder.PreviousOrderViewHolder
import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class PreviousOrderDelegate : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is PreviousOrder
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PreviousOrderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.viewholder_previous_order,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val previousOrderViewHolder = holder as? PreviousOrderViewHolder
        val item = items[position] as? PreviousOrder

        if (item != null) {
            previousOrderViewHolder?.setOrder(item)
        }
    }
}