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
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.delegates.ManageDrinksAdapter
import com.tobiapplications.menu.utils.extensions.*
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.general.SwipeDeleteCallback
import com.tobiapplications.menu.utils.general.SwipeDeleteCallbackHolder
import kotlinx.android.synthetic.main.fragment_manage_drinks.*
import kotlinx.android.synthetic.main.view_alertdialog_add_drink.view.*

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class ManageDrinksFragment : BaseFragment(), SwipeDeleteCallbackHolder {

    private lateinit var viewModel: ManageDrinksViewModel
    private var recyclerLayoutManager: LinearLayoutManager? = null
    private var manageDrinksAdapter: ManageDrinksAdapter? = null
    private var addDrinkDialog : AlertDialog? = null

    companion object {
        fun newInstance() : ManageDrinksFragment {
            return ManageDrinksFragment()
        }
    }

    override fun init() {
        initRecyclerView()
        initViews()
        initViewModel()
    }

    private fun initViews() {
        fabAdd.onClick { openAddDrinkDialog() }
    }

    private fun initRecyclerView() {
        recyclerLayoutManager = LinearLayoutManager(context)
        manageDrinksAdapter = ManageDrinksAdapter()
        val itemTouchHelper = ItemTouchHelper(SwipeDeleteCallback(this, getDrawable(R.drawable.ic_delete_white)!!, ColorDrawable(getColor(R.color.colorRed))))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        recyclerView.apply {
            layoutManager = recyclerLayoutManager
            adapter = manageDrinksAdapter
        }
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.drinks.observe(this, Observer {
            manageDrinksAdapter?.clear()
            manageDrinksAdapter?.setItems(it)
        })
        viewModel.addDrinkResult.observe(this, Observer { onAddDrinkResult(it) })
    }

    private fun openAddDrinkDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.view_alertdialog_add_drink, null)
        addDrinkDialog = AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.admin_add_drink_title))
                .setView(view)
                .setCancelable(false)
                .setPositiveButton(R.string.general_add, null)
                .setNeutralButton(R.string.admin_add_drink_clear_input, null)
                .setNegativeButton(R.string.general_cancel, null)
                .create()
        addDrinkDialog?.show()
        addDrinkDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.onClick {
            val name = view.name.text.toString()
            val size = view.size.text.toString()
            val price = view.price.text.toString()
            if (name.isEmpty() || size.isEmpty() || price.isEmpty()) {
                toast("Fehler")
            } else {
                view.closeKeyboard()
                view.inputLayout.hide()
                view.progress.show()
                viewModel.addNewDrink(Drink(name, size, price.toDouble()))
            }
        }

        addDrinkDialog?.getButton(AlertDialog.BUTTON_NEUTRAL)?.onClick {
            view.name.setText(Constants.EMPTY_STRING)
            view.size.setText(Constants.EMPTY_STRING)
            view.price.setText(Constants.EMPTY_STRING)
        }

    }

    private fun onAddDrinkResult(it: Boolean?) {
        toast(if (it == true) R.string.admin_add_drink_success else R.string.admin_add_drink_failed)

        addDrinkDialog?.apply {
            findViewById<ProgressBar>(R.id.progress)?.hide()
            findViewById<ConstraintLayout>(R.id.inputLayout)?.show()
        }
    }

    override fun onItemSwiped(adapterPosition: Int) {
        viewModel.deleteDrink(manageDrinksAdapter?.getItem(adapterPosition) as? Drink)
    }

    override fun isToolbarBackButtonEnabled(): Boolean {
        return true
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.admin_manage_drinks_toolbar_title)
    }

    override fun canModifyAppComponents(): Boolean {
        return true
    }

    override fun getLayout(): Int {
        return R.layout.fragment_manage_drinks
    }
}