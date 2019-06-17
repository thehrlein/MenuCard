package com.tobiapplications.menu.utils.repository.tobacco

import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.utils.repository.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
@Singleton
class TobaccoRepository @Inject constructor(localSource: TobaccoLocalSource,
                                            networkSource: TobaccoNetworkSource) : BaseRepository<Boolean, List<Tobacco>>(localSource, networkSource)