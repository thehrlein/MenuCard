package com.tobiapplications.menu.ui.viewholder.admin

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.model.admin.Tobacco
import kotlinx.android.synthetic.main.viewholder_admin_tobacco.view.*

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class AdminTobaccoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var tobacco : Tobacco? = null

    init {

    }

    fun setTobacco(tobacco: Tobacco) {
        this.tobacco = tobacco

        view.brand.text = tobacco.brand
        view.name.text = tobacco.name
    }


}