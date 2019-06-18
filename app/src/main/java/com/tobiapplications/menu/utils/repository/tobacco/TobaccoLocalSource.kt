package com.tobiapplications.menu.utils.repository.tobacco

import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.utils.repository.base.BaseLocalSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
@Singleton
class TobaccoLocalSource @Inject constructor() : BaseLocalSource<Boolean, List<Tobacco>>()