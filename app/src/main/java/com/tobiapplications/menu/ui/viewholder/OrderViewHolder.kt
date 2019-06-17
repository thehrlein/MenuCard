package com.tobiapplications.menu.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.order.OrderableItem
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.utils.extensions.formatEuro
import kotlinx.android.synthetic.main.viewholder_order_item.view.*

/**
 * Created by tobias.hehrlein on 2019-05-28.
 */
class OrderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setOrder(item: OrderableItem) {
        view.position.text = (adapterPosition + 1).toString()

        if (item is Shisha) {
            view.name.text = item.name
            view.price.text = item.price.formatEuro()
            view.count.text = item.count.toString()
        } else if (item is Drink) {
            view.size.text = item.size
            view.name.text = item.name
            view.price.text = item.price.formatEuro()
            view.count.text = item.count.toString()
        }
    }
}