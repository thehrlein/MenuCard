package com.tobiapplications.menu.ui.views.general

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.order.OrderableItem
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.utils.extensions.formatEuro
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.extensions.setVisible
import com.tobiapplications.menu.utils.general.Constants
import kotlinx.android.synthetic.main.view_admin_order_expandable_group.view.*
import kotlinx.android.synthetic.main.view_admin_order_expandable_list_row.view.*

/**
 * Created by tobias.hehrlein on 2019-06-24.
 */
class AdminExpandableView(context: Context, attrs: AttributeSet? = null, defStyleAtt: Int = 0) : LinearLayout(context, attrs, defStyleAtt) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_admin_order_expandable_group,  this)
        expandView.onClick {
            arrow.rotation = if (expandLayout.isExpanded) 0f else 180f
            separator.setVisible(expandLayout.isExpanded)
            expandLayout.toggle()
        }
    }

    fun setExpandText(text: String) {
        expandView.text = text
    }

    fun setExpandIcon(resId: Int) {
        icon.setBackgroundResource(resId)
    }

    fun setItems(items: List<OrderableItem>) {
        expandList.removeAllViews()
        for ((index, value) in items.withIndex()) {
            var name = Constants.EMPTY_STRING
            var size = Constants.EMPTY_STRING
            var price = Constants.EMPTY_STRING
            var count = Constants.EMPTY_STRING

            if (value is Drink) {
                with(value) {
                    name = this.name
                    size = this.size
                    price = this.price.formatEuro()
                    count = this.count.toString()
                }
            } else if (value is Shisha) {
                with(value) {
                    name = this.getTobaccoList()
                    price = this.price.formatEuro()
                    count = this.count.toString()
                }
            }

            val view = LayoutInflater.from(context).inflate(R.layout.view_admin_order_expandable_list_row, null)
            view.apply {
                this.position.text = "${index + 1}."
                this.name.text = name
                this.size.text = size
                this.price.text = price
                this.count.text = count
            }

            expandList.addView(view)
        }
    }

}