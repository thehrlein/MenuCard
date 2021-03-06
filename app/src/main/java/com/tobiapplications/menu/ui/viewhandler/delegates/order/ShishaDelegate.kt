package com.tobiapplications.menu.ui.viewhandler.delegates.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.ui.viewholder.addtoorder.ShishaViewHolder
import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class ShishaDelegate(private val onSelectTobaccoClicked: (Int, Shisha) -> Unit) : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is Shisha
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ShishaViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.viewholder_shisha, parent, false), onSelectTobaccoClicked
        )
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as? ShishaViewHolder
        val item = items[position] as? Shisha

        if (item != null) {
            viewHolder?.setShisha(item)
        }
    }
}