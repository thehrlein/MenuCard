package com.tobiapplications.menu.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.model.order.Drink
import com.tobiapplications.menu.model.order.OrderItem
import com.tobiapplications.menu.utils.extensions.formatEuro
import kotlinx.android.synthetic.main.viewholder_order_item.view.*

/**
 * Created by tobias.hehrlein on 2019-05-28.
 */
class OrderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    init {

    }

    fun setOrder(item: OrderItem) {
        view.position.text = (adapterPosition + 1).toString()
        view.name.text = item.name
        view.price.text = item.price.formatEuro()
        view.count.text = item.count.toString()

        if (item is Drink) {
            view.size.text = item.size
        }
    }
}