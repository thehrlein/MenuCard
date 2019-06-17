package com.tobiapplications.menu.ui.viewhandler.delegates.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.ui.viewholder.admin.AdminDrinkViewHolder
import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class AdminDrinkDelegate : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is Drink
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return AdminDrinkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_admin_drink, parent, false))
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as? AdminDrinkViewHolder
        val item = items[position] as? Drink

        if (item != null) {
            viewHolder?.setDrink(item)
        }
    }
}