package com.tobiapplications.menu.ui.viewhandler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.utils.extensions.setVisible

/**
 * Created by tobias.hehrlein on 2019-06-18.
 */
class TobaccoSelectionAdapter(context: Context, private val options: List<Tobacco>, private val selected: MutableList<Tobacco>, private val resId: Int = R.layout.view_tobacco_selection_row) : ArrayAdapter<Tobacco>(context, resId, options) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(resId, null)

        view.findViewById<TextView>(R.id.text).text = item?.getFullName()
        val isSelected = selected.firstOrNull { it.getFullName() == item?.getFullName() } != null
        view.findViewById<ImageView>(R.id.image).setVisible(isSelected)

        return view
    }

    fun toggle(position: Int) {
        val selectedTobacco = options[position]
        if (selected.contains(selectedTobacco)) {
            selected.remove(selectedTobacco)
        } else {
            selected.add(selectedTobacco)
        }
    }

    fun getSelectedTobaccos(): List<Tobacco> {
        return selected
    }
}