package com.tobiapplications.menu.domain

import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.order.OrderDrink
import com.tobiapplications.menu.utils.mvvm.BaseRepositoryRequestUseCase
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.repository.drinks.DrinksRepository
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class GetAllDrinksUseCase @Inject constructor(drinksRepository: DrinksRepository) : BaseRepositoryRequestUseCase<Boolean, List<OrderDrink>, List<Drink>>(drinksRepository) {

    override fun execute(parameters: Boolean) {
        if (parameters) {
            super.clear()
        }

        super.execute(parameters)
    }

    override fun transformResponse(input: List<Drink>?): List<OrderDrink> {
        return input?.map { OrderDrink(it.name, it.size, it.price, 0) }.orEmpty()
    }
}