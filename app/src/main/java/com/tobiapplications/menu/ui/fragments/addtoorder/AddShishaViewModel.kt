package com.tobiapplications.menu.ui.fragments.addtoorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.domain.general.GetAllTobaccosUseCase
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.utils.extensions.map
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class AddShishaViewModel @Inject constructor(getAllTobaccosUseCase: GetAllTobaccosUseCase) : ViewModel() {

    val tobaccos : LiveData<List<Tobacco>?>

    init {
        tobaccos = getAllTobaccosUseCase.observe().map {
            (it as? Result.Success<List<Tobacco>>)?.data
        }

        getAllTobaccosUseCase.execute(false)
    }
}