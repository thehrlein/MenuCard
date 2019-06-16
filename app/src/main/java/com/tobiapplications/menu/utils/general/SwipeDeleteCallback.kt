package com.tobiapplications.menu.utils.general

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class SwipeDeleteCallback(private val fragment: SwipeDeleteCallbackHolder, private val deleteIcon: Drawable, private val background: ColorDrawable) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        fragment.onItemSwiped(viewHolder.adapterPosition)
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val view = viewHolder.itemView
        val offset = 20

        val iconMargin = (view.height - deleteIcon.intrinsicHeight) / 2
        val iconTop = view.top + (view.height - deleteIcon.intrinsicHeight) / 2
        val iconBottom = iconTop + deleteIcon.intrinsicHeight
        when {
            dX > 0 -> onSwipeRight(view, offset, iconMargin, iconTop, iconBottom, dX)
            dX < 0 -> { }
            else -> background.setBounds(0, 0, 0, 0)
        }

        background.draw(c)
        deleteIcon.draw(c)

    }

    private fun onSwipeRight(view: View, offset: Int, iconMargin: Int, iconTop: Int, iconBottom: Int, dX: Float) {
        val iconLeft = view.left + iconMargin // + deleteIcon.intrinsicWidth
        val iconRight = view.left + deleteIcon.intrinsicWidth
        deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        background.setBounds(view.left, view.top, view.left + dX.toInt() + offset, view.bottom)
    }
}