package com.tobiapplications.menu.ui.viewholder.admin

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.model.order.Order
import kotlinx.android.synthetic.main.viewholder_admin_order.view.*

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
class AdminOrderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setOrder(order: Order) {
        view.date.text = order.timeStamp.toString()
        view.name.text = order.id
    }
}