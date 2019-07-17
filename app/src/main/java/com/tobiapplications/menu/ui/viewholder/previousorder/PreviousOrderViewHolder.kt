package com.tobiapplications.menu.ui.viewholder.previousorder

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.previousorders.PreviousOrder
import com.tobiapplications.menu.ui.views.general.AdminExpandableView
import com.tobiapplications.menu.utils.enums.OrderStatus
import com.tobiapplications.menu.utils.enums.OrderType
import com.tobiapplications.menu.utils.extensions.formatEuro
import com.tobiapplications.menu.utils.extensions.getColor
import com.tobiapplications.menu.utils.extensions.getString
import com.tobiapplications.menu.utils.extensions.setGone
import com.tobiapplications.menu.utils.general.DateUtils
import kotlinx.android.synthetic.main.viewholder_previous_order.view.*

/**
 *  Created by tobiashehrlein on 2019-06-03
 */
class PreviousOrderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setOrder(order: Order) {

        view.progress.setGone()
        view.date.text = "${DateUtils.getDate(order.timeStamp)} Uhr"
        setStatus(order.status)
        buildExpandableLayouts(order)
    }

    private fun setStatus(status: OrderStatus) {
        view.status.text = getString(status.textRes)
        val statusBackground = view.status.background as? GradientDrawable
        statusBackground?.setColor(getColor(status.colorRes))
    }

    private fun buildExpandableLayouts(order: Order) {
        view.orders.removeAllViews()
        if (order.drinks.isNotEmpty()) {
            val expandableView = AdminExpandableView(view.context)
            expandableView.setOrderType(OrderType.DRINK)
            expandableView.setExpandIcon(R.drawable.ic_fab_beer)
            expandableView.setItems(order.drinks)
            view.orders.addView(expandableView)
        }

        if (order.shisha.isNotEmpty()) {
            val expandableView = AdminExpandableView(view.context)
            expandableView.setOrderType(OrderType.SHISHA)
            expandableView.setExpandIcon(R.drawable.ic_fab_shisha)
            expandableView.setItems(order.shisha)
            view.orders.addView(expandableView)
        }

        val sumDrinks = order.drinks.sumByDouble { it.price * it.count }
        val sumShisha = order.shisha.sumByDouble { it.price * it.count }
        view.totalCount.text = (sumDrinks + sumShisha).formatEuro()
    }
}