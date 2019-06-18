package com.tobiapplications.menu.ui.viewholder.addtoorder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.utils.extensions.formatEuro
import com.tobiapplications.menu.utils.extensions.getString
import com.tobiapplications.menu.utils.extensions.onClick
import kotlinx.android.synthetic.main.viewholder_shisha.view.*

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
class ShishaViewHolder(private val view: View, onSelectTobaccoClicked: (Int, Shisha) -> Unit) : RecyclerView.ViewHolder(view) {

    private var shisha : Shisha? = null

    init {
        view.onClick {
            shisha?.let {
                onSelectTobaccoClicked.invoke(adapterPosition, it)
            }
        }
    }

    fun setShisha(shisha: Shisha) {
        this.shisha = shisha

        view.name.text = shisha.name
        view.price.text = shisha.price.formatEuro()
        if (shisha.tobaccos.isEmpty()) {
            view.tobaccos.text = getString(R.string.add_shisha_click_to_add_tobacco)
        } else {
            view.tobaccos.text = getTobaccoList(shisha.tobaccos)

        }
    }

    private fun getTobaccoList(tobaccos: List<Tobacco>) : String {
        var text = tobaccos.first().getFullName()
        tobaccos.filterIndexed { index, tobacco -> index > 0 }.forEach {
            text += ", ${it.getFullName()}"

        }

        return getString(R.string.add_shisha_tobaccos_placeholder, text)
    }

}