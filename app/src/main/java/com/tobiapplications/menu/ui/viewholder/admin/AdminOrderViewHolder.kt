package com.tobiapplications.menu.ui.viewholder.admin

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.ui.views.general.AdminExpandableView
import com.tobiapplications.menu.utils.extensions.show
import com.tobiapplications.menu.utils.general.DateUtils
import kotlinx.android.synthetic.main.view_admin_order_expandable_group.view.*
import kotlinx.android.synthetic.main.viewholder_admin_order.view.*

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
class AdminOrderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setOrder(order: Order) {
        view.date.text = "${DateUtils.getDate(order.timeStamp)} Uhr"
        view.name.text = order.id

        buildExpandableLayouts(order)
    }

    private fun buildExpandableLayouts(order: Order) {
        view.orders.removeAllViews()
        if (order.drinks.isNotEmpty()) {
            val expandableView = AdminExpandableView(view.context)
            expandableView.setExpandText("Getränke")
            expandableView.setExpandIcon(R.drawable.ic_fab_beer)
            expandableView.setItems(order.drinks)
            view.orders.addView(expandableView)
        }

        if (order.shisha.isNotEmpty()) {
            val expandableView = AdminExpandableView(view.context)
            expandableView.setExpandText("Shishas")
            expandableView.setExpandIcon(R.drawable.ic_fab_shisha)
            expandableView.setItems(order.shisha)
            view.orders.addView(expandableView)
        }
    }
}