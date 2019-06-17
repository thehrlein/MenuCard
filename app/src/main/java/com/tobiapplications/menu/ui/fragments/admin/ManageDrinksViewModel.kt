package com.tobiapplications.menu.ui.fragments.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.admin.AddDrinkToFireStoreUseCase
import com.tobiapplications.menu.domain.admin.DeleteDrinkFromFireStoreUseCase
import com.tobiapplications.menu.domain.admin.GetAndListenToAllDrinksUseCase
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class ManageDrinksViewModel @Inject constructor(getAndListenToAllDrinksUseCase: GetAndListenToAllDrinksUseCase,
                                                private val addDrinkToFireStoreUseCase: AddDrinkToFireStoreUseCase,
                                                private val deleteDrinkFromFireStoreUseCase: DeleteDrinkFromFireStoreUseCase) : ViewModel() {

    val drinks : LiveData<List<Drink>?>
    val addDrinkResult : LiveData<Boolean>

    init {
        drinks = getAndListenToAllDrinksUseCase.observe().map {
            (it as? Result.Success<List<Drink>>)?.data
        }

        addDrinkResult = addDrinkToFireStoreUseCase.observe().map {
            (it as? Result.Success<Boolean>)?.data.orFalse()
        }

        getAndListenToAllDrinksUseCase.execute(Unit)
    }

    fun addNewDrink(drink: Drink) {
        addDrinkToFireStoreUseCase.execute(drink)
    }

    fun deleteDrink(drink: Drink?) {
        if (drink == null) {
            return
        }

        deleteDrinkFromFireStoreUseCase.execute(drink)
    }
}