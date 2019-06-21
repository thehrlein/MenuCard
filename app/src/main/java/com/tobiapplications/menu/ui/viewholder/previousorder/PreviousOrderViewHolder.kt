package com.tobiapplications.menu.ui.viewholder.previousorder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.previousorders.PreviousOrder
import kotlinx.android.synthetic.main.viewholder_previous_order.view.*

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class PreviousOrderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    init {

    }

    fun setOrder(item: Order) {
        view.title.text = item.toString()
    }
}