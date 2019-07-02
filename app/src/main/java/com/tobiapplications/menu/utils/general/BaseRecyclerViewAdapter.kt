package com.tobiapplications.menu.utils.general

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.utils.extensions.hasItems
import com.tobiapplications.menu.utils.extensions.orDefault

/**
 * Created by tobias.hehrlein on 07.02.2019.
 */
open class BaseRecyclerViewAdapter(delegates: List<AdapterDelegate<List<DisplayableItem>>> = emptyList()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val INVALID_INDEX = -1
    }

    var itemList : MutableList<DisplayableItem> = ArrayList()
    var delegatesManager: AdapterDelegatesManager<List<DisplayableItem>> = AdapterDelegatesManager()

    init {
        delegates.forEach { delegatesManager.addDelegate(it) }
    }

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

    /** Set list of item */
    fun setItems(items: List<DisplayableItem>?) {
        items?.let {
            itemList.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun setItems(items: List<DisplayableItem>?, clearAll: Boolean) {
        if (clearAll && listNotEmpty()) {
            itemList.clear()
        }

        val startIndex = itemCount
        items?.let {
            itemList.addAll(it)
        }

        if (clearAll) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeChanged(startIndex, items?.size.orDefault())
        }
    }

    fun addNonExtisting(items: List<DisplayableItem>?) {
        val list = items?.filterNot { itemList.contains(it) }
        setItems(list)
    }

    fun <T : DisplayableItem> update(item: T, clazz: Class<T>, find: (T, T) -> Boolean, modify: (T, T) -> Unit) {
        val a = itemList.filterIsInstance(clazz).firstOrNull { find(it, item) } ?: return
        modify(a, item)
        notifyItemChanged(a)
    }

    /** add single item */
    fun addItem(item: DisplayableItem) {
        itemList.add(item)
        notifyItemInserted(item)
    }

    /** remove item(s) */
    fun clear() {
        if (listNotEmpty()) {
            itemList.clear()
            notifyDataSetChanged()
        }
    }

    fun removeItem(item: DisplayableItem) {
        val pos = indexOf(item)
        if (positionInRange(pos)) {
            itemList.remove(item)
            notifyItemRemoved(pos)
        }
    }

    /** notifying */
    fun notifyItemInserted(item: DisplayableItem) {
        val index = indexOf(item)
        if (index != INVALID_INDEX) {
            notifyItemInserted(index)
        }
    }

    fun notifyRemoved(index: Int) {
        if (index != INVALID_INDEX) {
            notifyItemRemoved(index)
        }
    }

    fun notifyItemChanged(item: DisplayableItem) {
        val index = indexOf(item)
        if (index != INVALID_INDEX) {
            notifyItemChanged(index)
        }
    }

    /** helper methods */
    fun listNotEmpty(): Boolean {
        return itemList.hasItems()
    }

    fun indexOf(item: DisplayableItem?): Int {
        return if (itemList.contains(item)) { itemList.indexOf(item) } else INVALID_INDEX
    }

    fun indexOf(className: Class<out DisplayableItem>): Int {
        return indexOf(itemList.firstOrNull { className.isInstance(it) })
    }

    fun positionInRange(position: Int): Boolean {
        return itemList.hasItems() && !(position < 0 || position >= itemCount)
    }

    fun itemAt(position: Int): DisplayableItem? {
        return if (!positionInRange(position)) null else itemList[position]
    }

    fun getItem(adapterPosition: Int): DisplayableItem? {
        return if (positionInRange(adapterPosition)) itemList[adapterPosition] else null
    }

}

