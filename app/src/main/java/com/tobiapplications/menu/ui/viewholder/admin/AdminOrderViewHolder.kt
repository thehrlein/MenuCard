package com.tobiapplications.menu.ui.viewholder.admin

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.UpdateDataModel
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.ui.views.general.AdminExpandableView
import com.tobiapplications.menu.utils.enums.OrderStatus
import com.tobiapplications.menu.utils.enums.OrderType
import com.tobiapplications.menu.utils.extensions.formatEuro
import com.tobiapplications.menu.utils.extensions.getColor
import com.tobiapplications.menu.utils.extensions.getString
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.general.DateUtils
import kotlinx.android.synthetic.main.viewholder_admin_order.view.*

/**
 *  Created by tobiashehrlein on 2019-06-23
 */
class AdminOrderViewHolder(private val view: View, newStatus: (UpdateDataModel) -> Unit) : RecyclerView.ViewHolder(view) {

    private var order: Order? = null

    init {
        view.prevStatus.onClick {
            order?.let { order ->
                val prevStatus = OrderStatus.getPrevStatus(order.status)
                if (prevStatus != null)
                    newStatus(UpdateDataModel(
                        Constants.ORDER_COLLECTION,
                        order.id!!,
                        Constants.STATUS_FIELD,
                        prevStatus))
            }
        }

        view.nextStatus.onClick {
            order?.let { order ->
                val nextStatus = OrderStatus.getNextStatus(order.status)
                if (nextStatus != null)
                    newStatus(UpdateDataModel(
                        Constants.ORDER_COLLECTION,
                        order.id!!,
                        Constants.STATUS_FIELD,
                        nextStatus))
            }
        }
    }

    fun setOrder(order: Order) {
        this.order = order
        view.date.text = "${DateUtils.getDate(order.timeStamp)} Uhr"
        view.name.text = order.id

        setStatus(order.status)
        buildExpandableLayouts(order)
    }

    private fun setStatus(status: OrderStatus) {
        view.status.text = getString(status.textRes)
        val statusBackground = view.status.background as? GradientDrawable
        statusBackground?.setColor(getColor(status.colorRes))

        OrderStatus.getPrevStatus(status)?.textRes?.let {
            view.prevStatus.text = getString(it)
        }

        OrderStatus.getNextStatus(status)?.textRes?.let {
            view.nextStatus.text = getString(it)
        }
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