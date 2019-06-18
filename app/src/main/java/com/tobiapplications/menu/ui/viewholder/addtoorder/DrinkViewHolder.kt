package com.tobiapplications.menu.ui.viewholder.addtoorder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.utils.extensions.formatEuro
import kotlinx.android.synthetic.main.viewholder_drink.view.*

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class DrinkViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var orderDrink : Drink? = null

    init {
        view.orderControl.setOnValueChangeListener { orderDrink?.count = it }
    }

    fun setDrink(item: Drink) {
        this.orderDrink = item

        view.name.text = item.name
        view.size.text = item.size
        view.price.text = item.price.formatEuro()
    }


}