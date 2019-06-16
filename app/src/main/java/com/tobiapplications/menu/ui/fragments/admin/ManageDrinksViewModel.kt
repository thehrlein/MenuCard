package com.tobiapplications.menu.ui.fragments.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.admin.AddDrinkToFireStoreUseCase
import com.tobiapplications.menu.domain.admin.GetAllDrinksUseCase
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class ManageDrinksViewModel @Inject constructor(getAllDrinksUseCase: GetAllDrinksUseCase,
                                                private val addDrinkToFireStoreUseCase: AddDrinkToFireStoreUseCase) : ViewModel() {

    val drinks : LiveData<List<Drink>?>
    val addDrinkResult : LiveData<Boolean>

    init {
        drinks = getAllDrinksUseCase.observe().map {
            (it as? Result.Success<List<Drink>>)?.data
        }

        addDrinkResult = addDrinkToFireStoreUseCase.observe().map {
            (it as? Result.Success<Boolean>)?.data.orFalse()
        }

        getAllDrinksUseCase.execute(Unit)
    }

    fun addNewDrink(drink: Drink) {
        addDrinkToFireStoreUseCase.execute(drink)
    }
}