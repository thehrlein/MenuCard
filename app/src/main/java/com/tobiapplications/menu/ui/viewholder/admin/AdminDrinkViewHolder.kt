package com.tobiapplications.menu.ui.viewholder.admin

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.utils.extensions.formatEuro
import kotlinx.android.synthetic.main.viewholder_admin_drink.view.*

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class AdminDrinkViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var drink : Drink? = null

    init {

    }

    fun setDrink(item: Drink) {
        this.drink = item

        view.name.text = item.name
        view.size.text = item.size
        view.price.text = item.price.formatEuro()
    }


}