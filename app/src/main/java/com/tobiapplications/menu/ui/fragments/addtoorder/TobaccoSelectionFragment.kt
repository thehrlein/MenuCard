package com.tobiapplications.menu.ui.fragments.addtoorder

import android.os.Bundle
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.model.order.TobaccoSelectionModel
import com.tobiapplications.menu.ui.fragments.base.BaseDialogFragment
import com.tobiapplications.menu.ui.viewhandler.adapter.TobaccoSelectionAdapter
import com.tobiapplications.menu.utils.extensions.*
import com.tobiapplications.menu.utils.general.Constants
import kotlinx.android.synthetic.main.fragment_tobacco_selection.*

/**
 * Created by tobias.hehrlein on 2019-06-18.
 */
class TobaccoSelectionFragment : BaseDialogFragment() {

    private var tobaccoSelectedListener: ((Int, List<Tobacco>) -> Unit)? = null
    private var adapter: TobaccoSelectionAdapter? = null

    companion object {
        const val MIN_POS_FOR_SCROLL = 5
        const val SCROLL_DURATION = 500
        const val SCROLL_DELAY = 500L

        fun newInstance(selection: TobaccoSelectionModel) : TobaccoSelectionFragment {
            val bundle = Bundle().apply { putSerializable(Constants.TOBACCO_SELECTION_MODEL, selection) }
            return TobaccoSelectionFragment().apply { arguments = bundle }
        }
    }

    override fun init() {
        initToolbar()
        initViews()
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white)
        toolbar.setNavigationOnClickListener { close() }
    }

    private fun initViews() {

        val selectionModel = arguments?.getSerializable(Constants.TOBACCO_SELECTION_MODEL) as? TobaccoSelectionModel
        if (selectionModel == null) {
            toast(R.string.general_unknown_error)
            return
        }

        select.onClick {
            tobaccoSelectedListener?.invoke(selectionModel.pos, adapter?.getSelectedTobaccos().orEmpty())
            closeDelayed()
        }

        adapter = TobaccoSelectionAdapter(
            requireContext(),
            selectionModel.options,
            selectionModel.selected.toMutableList()
        )
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            adapter?.toggle(position)
            adapter?.notifyDataSetChanged()
        }

        postDelayed( {
            val firstPos = selectionModel.options.indexOf(selectionModel.selected.firstOrNull())
            if (firstPos > MIN_POS_FOR_SCROLL) {
                listView?.smoothScrollToPositionFromTop(firstPos, 0, SCROLL_DURATION)
            }
        }, SCROLL_DELAY)
    }

    private fun closeDelayed() {
        postDelayed( { close() }, 300)
    }

    fun setOnTobaccoSelectedListener(listener: (Int, List<Tobacco>) -> Unit) {
        this.tobaccoSelectedListener = listener
    }

    override fun getLayout(): Int {
        return R.layout.fragment_tobacco_selection
    }
}