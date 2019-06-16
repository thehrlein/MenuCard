package com.tobiapplications.menu.ui.viewhandler.delegates.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.order.OrderDrink
import com.tobiapplications.menu.ui.viewholder.DrinkViewHolder
import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class DrinkDelegate : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is OrderDrink
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return DrinkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_drink, parent, false))
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as? DrinkViewHolder
        val item = items[position] as? OrderDrink

        if (item != null) {
            viewHolder?.setDrink(item)
        }
    }
}