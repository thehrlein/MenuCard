package com.tobiapplications.menu.ui.viewhandler.delegates.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.ui.viewholder.admin.AdminTobaccoViewHolder
import com.tobiapplications.menu.utils.general.DisplayableItem

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class AdminTobaccoDelegate : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is Tobacco
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return AdminTobaccoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_admin_tobacco, parent, false))
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as? AdminTobaccoViewHolder
        val item = items[position] as? Tobacco

        if (item != null) {
            viewHolder?.setTobacco(item)
        }
    }
}