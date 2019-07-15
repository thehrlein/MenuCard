package com.tobiapplications.menu.domain.admin

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.admin.ManageDataModel
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.model.order.UserOrder
import com.tobiapplications.menu.utils.enums.OrderStatus
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-07-02
 */
class GetAndListenToAllOrdersUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<ManageDataModel, List<Order>>() {

    private var snapShotListener : ListenerRegistration? = null
    private var dataModel : ManageDataModel? = null

    override fun execute(parameters: ManageDataModel) {
        this.dataModel = parameters
        registerSnapSnotListener()
    }

    private fun registerSnapSnotListener() {
        val model = dataModel
        if (model == null) {
            onFailure()
        } else {
            snapShotListener = fireStore.collection(model.collection)
                .addSnapshotListener { snapShot, exception ->
                    if (exception != null) {
                        onFailure()
                    }

                    val items = mutableListOf<Order>()
                    snapShot?.let {
                        for (doc in it) {
//                            item.id = doc.id
                            val b = doc.data as? HashMap<String, HashMap<String, Any>> ?: continue

                            b.forEach {
                                val drinks = buildDrinks(it.value.get("drinks") as ArrayList<Map<String, Any>>)
                                val shisha = buildShishas(it.value.get("shisha") as ArrayList<Map<String, Any>>)
                                val timeStamp = it.value.get("timeStamp") as Long
                                val status = OrderStatus.getStatus(it.value.get("status") as String)
                                items.add(Order(drinks, shisha, timeStamp, status))
                            }

                        }
                    }

                    onSuccess(items)
                }
        }
    }

    private fun buildDrinks(list: List<Map<String, Any>>): MutableList<Drink> {
        val drinks = mutableListOf<Drink>()
        list.forEach {
            val name = it["name"] as String
            val size = it["size"] as String
            val price = it["price"] as Double
            val count = it["count"] as Long
            drinks.add(Drink(name, size, price, count))
        }
        return drinks
    }

    private fun buildShishas(list: List<Map<String, Any>>): MutableList<Shisha> {
        val shishas = mutableListOf<Shisha>()
        list.forEach {
            val name = it["name"] as String
            val tobaccos = buildTobaccos(it["tobaccos"] as? List<Map<String, String>> ?: emptyList())
            val price = it["price"] as Double
            val count = it["count"] as Long
            shishas.add(Shisha(name, tobaccos, price, count))
        }
        return shishas
    }

    private fun buildTobaccos(list: List<Map<String, String>>): List<Tobacco> {
        val tobaccos = mutableListOf<Tobacco>()
        list.forEach {
            val name = it["name"] as String
            val brand = it["brand"] as String
            tobaccos.add(Tobacco(brand, name))
        }

        return tobaccos
    }

    private fun onSuccess(orders: List<Order>) {
        result.postValue(Result.Success(orders))
    }

    private fun onFailure() {
        result.postValue(Result.Success(emptyList()))
    }

    fun removeSnapShotListener() {
        snapShotListener?.remove()
    }

    fun addSnapShotListener() {
        registerSnapSnotListener()
    }

}

class AA(val map : Map<String, UserOrder>) {
    constructor() : this(emptyMap())
}
