package com.tobiapplications.menu.domain.general

import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.utils.mvvm.BaseRepositoryRequestUseCase
import com.tobiapplications.menu.utils.repository.tobacco.TobaccoRepository
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class GetAllTobaccosUseCase @Inject constructor(tobaccoRepository: TobaccoRepository) : BaseRepositoryRequestUseCase<Boolean, List<Tobacco>, List<Tobacco>>(tobaccoRepository) {

    override fun execute(parameters: Boolean) {
        if (parameters) {
            super.clear()
        }

        super.execute(parameters)
    }

    override fun transformResponse(input: List<Tobacco>?): List<Tobacco> {
        return input.orEmpty()
    }
}