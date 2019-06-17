package com.tobiapplications.menu.utils.repository.drinks

import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.utils.repository.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
@Singleton
class DrinksRepository @Inject constructor(localSource: DrinksLocalSource,
                                           networkSource: DrinksNetworkSource) : BaseRepository<Boolean, List<Drink>>(localSource, networkSource)