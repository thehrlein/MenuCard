package com.tobiapplications.menu.ui.fragments.addtoorder

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.model.order.TobaccoSelectionModel
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.adapter.AddShishaAdapter
import com.tobiapplications.menu.utils.extensions.obtainViewModel
import com.tobiapplications.menu.utils.extensions.onClick
import com.tobiapplications.menu.utils.extensions.orDefault
import com.tobiapplications.menu.utils.extensions.show
import com.tobiapplications.menu.utils.general.OrderUtils
import kotlinx.android.synthetic.main.fragment_add_shisha.*
import nl.dionsegijn.steppertouch.OnStepCallback

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class AddShishaFragment : BaseFragment() {

    private lateinit var viewModel: AddShishaViewModel
    private var shishaAdapter: AddShishaAdapter? = null
    private var tobaccos: List<Tobacco>? = null

    companion object {
        fun newInstance() : AddShishaFragment {
            return AddShishaFragment()
        }
    }

    override fun init() {
        initRecyclerView()
        initViews()
        initViewModel()
    }

    private fun initRecyclerView() {
        shishaAdapter =
            AddShishaAdapter { pos, selected ->
                selectTobacco(
                    pos,
                    selected
                )
            }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shishaAdapter
        }
    }

    private fun selectTobacco(pos: Int, selected: Shisha) {
        val possibleTobaccos = tobaccos ?: return
        val tobaccoSelectionFragment = TobaccoSelectionFragment.newInstance(TobaccoSelectionModel(possibleTobaccos, selected.tabaccos, pos))
        tobaccoSelectionFragment.setOnTobaccoSelectedListener { pos, tobaccos ->
            val item = shishaAdapter?.getItem(pos) as? Shisha
            item?.tabaccos = tobaccos
            shishaAdapter?.notifyItemChanged(pos)
        }

        tobaccoSelectionFragment.show(childFragmentManager.beginTransaction())
    }

    private fun initViews() {
        stepper.apply {
            minValue = 0
            maxValue = 10
            sideTapEnabled = true
            addStepCallback(object : OnStepCallback {
                override fun onStep(value: Int, positive: Boolean) {
                    val adapter = shishaAdapter ?: return
                    val prevCount = adapter.itemCount
                    if (prevCount.orDefault() < value) {
                        val newItems = value - prevCount
                        repeat(newItems) { adapter.addItem(Shisha(getString(R.string.add_shisha_base_shisha_name, value))) }
                    } else if (prevCount > value) {
                        adapter.itemList = adapter.itemList.subList(0, value).toMutableList()
                        adapter.notifyItemRangeRemoved(value, prevCount)
                    }
                }
            })
        }

        add.onClick { onAddShisha() }
    }

    private fun onAddShisha() {
        val shishas = shishaAdapter?.itemList.orEmpty().map { it as Shisha }
        val valid = shishas.firstOrNull { it.tabaccos.isNullOrEmpty() } == null

        if (valid) {
            shishas.forEach { it.count = 1 }
            OrderUtils.addShishas(shishas)
            activity?.onBackPressed()

        } else {
            showNoTobaccoSelectedAlert()
        }
    }

    private fun showNoTobaccoSelectedAlert() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.general_attention))
            .setMessage(getString(R.string.add_shisha_not_all_shisha_have_tobacco_message))
            .setCancelable(false)
            .setPositiveButton(R.string.add_shisha_not_all_shisha_have_tobacco_select, null)
            .setNegativeButton(R.string.add_shisha_not_all_shisha_have_tobacco_delete) { _, _ -> deleteNotChoosenShisha() }
            .show()
    }

    private fun deleteNotChoosenShisha() {
        val noTobaccoSelected =
            shishaAdapter
                ?.itemList
                ?.map { it as? Shisha }
                ?.filter { it?.tabaccos.isNullOrEmpty() }

        noTobaccoSelected?.forEach {
            if (it != null) {
                shishaAdapter?.removeItem(it)
            }
        }

        stepper.count = shishaAdapter?.itemCount.orDefault()
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.tobaccos.observe(this, Observer { tobaccos = it })
    }

    override fun getLayout(): Int {
        return R.layout.fragment_add_shisha
    }
}