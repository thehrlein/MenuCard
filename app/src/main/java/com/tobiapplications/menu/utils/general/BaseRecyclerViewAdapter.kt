package com.tobiapplications.menu.utils.general

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

/**
 * Created by tobias.hehrlein on 07.02.2019.
 */
open class BaseRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemList : MutableList<DisplayableItem> = ArrayList()
    var delegatesManager: AdapterDelegatesManager<List<DisplayableItem>> = AdapterDelegatesManager()

    /** ViewHolder Methods **/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(itemList, position, holder)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(itemList, position)
    }

    /** Convenience Methods **/

    fun setItems(items: List<DisplayableItem>?) {
        items?.forEach { itemList.add(it) }
        notifyDataSetChanged()
    }
}

