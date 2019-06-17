package com.tobiapplications.menu.ui.fragments.addtoorder

import androidx.recyclerview.widget.LinearLayoutManager
import com.tobiapplications.menu.R
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import com.tobiapplications.menu.ui.viewhandler.AddShishaAdapter
import com.tobiapplications.menu.ui.viewhandler.delegates.order.ShishaDelegate
import com.tobiapplications.menu.utils.extensions.orDefault
import com.tobiapplications.menu.utils.general.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_add_shisha.*
import nl.dionsegijn.steppertouch.OnStepCallback

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class AddShishaFragment : BaseFragment() {

    private var shishaAdapter: AddShishaAdapter? = null

    companion object {
        fun newInstance() : AddShishaFragment {
            return AddShishaFragment()
        }
    }

    override fun init() {
        initRecyclerView()
        initViews()
    }

    private fun initRecyclerView() {
        shishaAdapter = AddShishaAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shishaAdapter
        }
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
    }

    override fun getLayout(): Int {
        return R.layout.fragment_add_shisha
    }
}