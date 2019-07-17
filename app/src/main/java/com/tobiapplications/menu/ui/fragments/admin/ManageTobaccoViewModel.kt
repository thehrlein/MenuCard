package com.tobiapplications.menu.ui.fragments.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.general.AddToFireStoreUseCase
import com.tobiapplications.menu.domain.admin.DeleteFromFireStoreUseCase
import com.tobiapplications.menu.domain.admin.GetAndListenToAllDataUseCase
import com.tobiapplications.menu.model.admin.AddToFireStoreModel
import com.tobiapplications.menu.model.admin.DeleteDataModel
import com.tobiapplications.menu.model.admin.ManageDataModel
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.extensions.orFalse
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-06-16
 */
class ManageTobaccoViewModel @Inject constructor(getAndListenToAllDataUseCase: GetAndListenToAllDataUseCase,
                                                 private val addToFireStoreUseCase: AddToFireStoreUseCase,
                                                 private val deleteFromFireStoreUseCase: DeleteFromFireStoreUseCase) : ViewModel() {

    val tobaccos : LiveData<List<Tobacco>?>
    val addTobaccoResult : LiveData<Boolean>

    init {
        tobaccos = getAndListenToAllDataUseCase.observe().map {
            (it as? Result.Success<List<Tobacco>>)?.data
        }

        addTobaccoResult = addToFireStoreUseCase .observe().map {
            (it as? Result.Success<Boolean>)?.data.orFalse()
        }

        getAndListenToAllDataUseCase.execute(ManageDataModel(Constants.TOBACCO_COLLECTION, clazz = Tobacco::class.java))
    }

    fun addNewTobacco(tobacco: Tobacco) {
        addToFireStoreUseCase.execute(AddToFireStoreModel(Constants.TOBACCO_COLLECTION, tobacco))
    }

    fun deleteTobacco(tobacco: Tobacco?) {
        tobacco?.let { deleteFromFireStoreUseCase.execute(DeleteDataModel(Constants.TOBACCO_COLLECTION, null, it.id.orEmpty())) }
    }
}