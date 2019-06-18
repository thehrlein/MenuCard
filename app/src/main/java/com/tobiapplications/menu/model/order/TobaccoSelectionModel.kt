package com.tobiapplications.menu.model.order

import com.tobiapplications.menu.model.admin.Tobacco
import java.io.Serializable

/**
 * Created by tobias.hehrlein on 2019-06-18.
 */
data class TobaccoSelectionModel(val options: List<Tobacco>,
                                 val selected: List<Tobacco>,
                                 val pos: Int) : Serializable