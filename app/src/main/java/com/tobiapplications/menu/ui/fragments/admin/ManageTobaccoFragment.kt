package com.tobiapplications.menu.ui.fragments.admin

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.ManageTobaccoAdapter
import com.tobiapplications.menu.utils.extensions.*
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.general.SwipeDeleteCallback
import com.tobiapplications.menu.utils.general.SwipeDeleteCallbackHolder
import kotlinx.android.synthetic.main.fragment_manage_tobacco.*
import kotlinx.android.synthetic.main.view_alertdialog_add_tobacco.view.*

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class ManageTobaccoFragment : BaseFragment(), SwipeDeleteCallbackHolder {

    private lateinit var viewModel: ManageTobaccoViewModel
    private var manageTobaccoAdapter: ManageTobaccoAdapter? = null
    private var addTobaccoDialog : AlertDialog? = null

    companion object {
        fun newInstance() : ManageTobaccoFragment {
            return ManageTobaccoFragment()
        }
    }

    override fun init() {
        initRecyclerView()
        initViews()
        initViewModel()
    }

    private fun initViews() {
        fabAdd.onClick { openAddTobaccoDialog() }
    }

    private fun initRecyclerView() {
        manageTobaccoAdapter = ManageTobaccoAdapter()
        val itemTouchHelper = ItemTouchHelper(SwipeDeleteCallback(this, getDrawable(R.drawable.ic_delete_white)!!, ColorDrawable(getColor(R.color.colorRed))))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = manageTobaccoAdapter
        }
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.tobaccos.observe(this, Observer { setTobaccos(it) })
        viewModel.addTobaccoResult.observe(this, Observer { onAddTobaccoResult(it) })
    }

    private fun setTobaccos(it: List<Tobacco>?) {
        manageTobaccoAdapter?.clear()

        if (it.isNullOrEmpty()) {
            errorNoTobacco.show()
        } else {
            errorNoTobacco.setGone()
            manageTobaccoAdapter?.setItems(it)
            recyclerView.show()
        }

        fabAdd.show()
        progress.hide()
    }

    private fun openAddTobaccoDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.view_alertdialog_add_tobacco, null)
        addTobaccoDialog = AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.admin_add_tobacco_title))
                .setView(view)
                .setCancelable(false)
                .setPositiveButton(R.string.general_add, null)
                .setNeutralButton(R.string.general_delete, null)
                .setNegativeButton(R.string.general_cancel, null)
                .create()
        addTobaccoDialog?.show()
        addTobaccoDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.onClick {
            val brand = view.brand.text.toString()
            val name = view.name.text.toString()
            if (brand.isEmpty() || name.isEmpty()) {
                toast("Fehler")
            } else {
                view.closeKeyboard()
                view.inputLayout.hide()
                view.progress.show()
                viewModel.addNewTobacco(Tobacco(brand, name))
            }
        }

        addTobaccoDialog?.getButton(AlertDialog.BUTTON_NEUTRAL)?.onClick {
            view.brand.setText(Constants.EMPTY_STRING)
            view.name.setText(Constants.EMPTY_STRING)
        }
    }

    private fun onAddTobaccoResult(it: Boolean?) {
        toast(if (it == true) R.string.admin_add_drink_success else R.string.admin_add_drink_failed)

        addTobaccoDialog?.apply {
            findViewById<ProgressBar>(R.id.progress)?.hide()
            findViewById<ConstraintLayout>(R.id.inputLayout)?.show()
        }
    }


    override fun onItemSwiped(adapterPosition: Int) {
        viewModel.deleteTobacco(manageTobaccoAdapter?.getItem(adapterPosition) as? Tobacco)
    }

    override fun isToolbarBackButtonEnabled(): Boolean {
        return true
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.admin_manage_shisha_toolbar_title)
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    override fun getLayout(): Int {
        return R.layout.fragment_manage_tobacco
    }
}