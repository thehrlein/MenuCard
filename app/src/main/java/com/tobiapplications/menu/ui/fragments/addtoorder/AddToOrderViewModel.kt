package com.tobiapplications.menu.ui.fragments.addtoorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.GetAllDrinksUseCase
import com.tobiapplications.menu.domain.GetAllTobaccosUseCase
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.utils.enums.OrderType
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class AddToOrderViewModel @Inject constructor(private val getAllDrinksUseCase: GetAllDrinksUseCase,
                                              private val getAllTobaccosUseCase: GetAllTobaccosUseCase) : ViewModel() {

    val drinks : LiveData<List<Drink>?>

    init {
        drinks = getAllDrinksUseCase.observe().map {
            (it as? Result.Success<List<Drink>>)?.data
        }

    }

    fun getData(orderType: OrderType?) {
        if (orderType == OrderType.DRINKS) {
            getAllDrinksUseCase.execute(false)
        }
    }
}