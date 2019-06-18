package com.tobiapplications.menu.utils.repository.drinks

import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.utils.repository.base.BaseLocalSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
@Singleton
class DrinksLocalSource @Inject constructor() : BaseLocalSource<Boolean, List<Drink>>()