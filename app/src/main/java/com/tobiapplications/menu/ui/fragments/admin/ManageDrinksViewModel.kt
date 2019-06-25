package com.tobiapplications.menu.ui.fragments.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.general.AddToFireStoreUseCase
import com.tobiapplications.menu.domain.admin.DeleteFromFireStoreUseCase
import com.tobiapplications.menu.domain.admin.GetAndListenToAllDataUseCase
import com.tobiapplications.menu.model.admin.AddToFireStoreModel
import com.tobiapplications.menu.model.admin.DeleteDataModel
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.admin.ManageDataModel
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class ManageDrinksViewModel @Inject constructor(getAndListenToAllDataUseCase: GetAndListenToAllDataUseCase,
                                                private val addToFireStoreUseCase: AddToFireStoreUseCase,
                                                private val deleteFromFireStoreUseCase: DeleteFromFireStoreUseCase) : ViewModel() {

    val drinks : LiveData<List<Drink>?>
    val addDrinkResult : LiveData<Boolean>

    init {
        drinks = getAndListenToAllDataUseCase.observe().map {
            (it as? Result.Success<List<Drink>>)?.data
        }

        addDrinkResult = addToFireStoreUseCase.observe().map {
            (it as? Result.Success<Boolean>)?.data.orFalse()
        }

        getAndListenToAllDataUseCase.execute(ManageDataModel(Constants.DRINK_COLLECTION, Drink::class.java))
    }

    fun addNewDrink(drink: Drink) {
        addToFireStoreUseCase.execute(AddToFireStoreModel(Constants.DRINK_COLLECTION, drink))
    }

    fun deleteDrink(drink: Drink?) {
        drink?.let { deleteFromFireStoreUseCase.execute(DeleteDataModel(Constants.DRINK_COLLECTION, it.id.orEmpty())) }
    }
}