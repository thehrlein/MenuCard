package com.tobiapplications.menu.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.utils.extensions.formatEuro
import com.tobiapplications.menu.utils.extensions.getString
import com.tobiapplications.menu.utils.extensions.setGone
import com.tobiapplications.menu.utils.extensions.show
import com.tobiapplications.menu.utils.general.Constants
import kotlinx.android.synthetic.main.viewholder_shisha.view.*

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class ShishaViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var shisha : Shisha? = null

    fun setShisha(shisha: Shisha) {
        this.shisha = shisha

        view.name.text = shisha.name
        view.price.text = shisha.price.formatEuro()
        if (shisha.tabaccos.isEmpty()) {
            view.tobaccos.setGone()
        } else {
            view.tobaccos.text = getTobaccoList(shisha.tabaccos)
            view.tobaccos.show()
        }
    }

    private fun getTobaccoList(tobaccos: List<Tobacco>) : String {
        var text = tobaccos.first().getFullName()
        tobaccos.forEach {
            text += ", ${it.getFullName()}"
        }

        return view.getString(R.string.add_shisha_tobaccos_placeholder, text)
    }

}