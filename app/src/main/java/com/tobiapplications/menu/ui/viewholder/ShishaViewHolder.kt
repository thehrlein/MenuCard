package com.tobiapplications.menu.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.model.Shisha
import com.tobiapplications.menu.utils.extensions.formatEuro
import kotlinx.android.synthetic.main.viewholder_shisha.view.*

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class ShishaViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var shisha : Shisha? = null

    init {
        view.orderControl.setOnValueChangeListener { shisha?.count = it }
    }

    fun setShisha(shisha: Shisha) {
        this.shisha = shisha

        view.name.text = shisha.name
        view.price.text = shisha.price.formatEuro()
    }


}