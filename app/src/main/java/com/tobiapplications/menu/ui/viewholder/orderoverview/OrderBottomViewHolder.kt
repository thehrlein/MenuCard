package com.tobiapplications.menu.ui.viewholder.orderoverview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.model.orderoverview.OrderOverviewItem
import com.tobiapplications.menu.utils.extensions.onClick
import kotlinx.android.synthetic.main.viewholder_order_overview_bottom.view.*

/**
 * Created by tobias.hehrlein on 2019-06-24.
 */
class OrderBottomViewHolder(private val view: View, onSendClicked: () -> Unit) : RecyclerView.ViewHolder(view) {

    init {
        view.confirmOrder.onClick { onSendClicked() }
    }

    fun setItem(item: OrderOverviewItem) {
        view.totalPrice.text = item.totalPrice
    }


}