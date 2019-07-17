package com.tobiapplications.menu.domain.admin

import com.google.firebase.firestore.FirebaseFirestore
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.model.admin.ManageDataModel
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.model.order.Order
import com.tobiapplications.menu.model.order.Shisha
import com.tobiapplications.menu.utils.enums.OrderStatus
import com.tobiapplications.menu.utils.mvvm.MediatorUseCase
import com.tobiapplications.menu.utils.mvvm.Result
import javax.inject.Inject

/**
 *  Created by tobiashehrlein on 2019-07-02
 */
class GetAndListenToAllOrdersUseCase @Inject constructor(private val fireStore: FirebaseFirestore) : MediatorUseCase<ManageDataModel, List<Order>>() {


    override fun execute(parameters: ManageDataModel) {
        // get all orders, no specific user
        if (parameters.document == null) {
            fireStore.collection(parameters.collection)
                .addSnapshotListener { snapShot, exception ->
                    if (exception != null) {
                        onFailure()
                    }

                    val items = mutableListOf<Order>()
                    snapShot?.let {
                        for (doc in it) {
                            val id = doc.id
                            val data = doc.data as? HashMap<String, HashMap<String, Any>>
                            items.addAll(buildItems(data, id))
                        }
                    }

                    onSuccess(items)
                }
        }
        // get only orders of one specific user (for his previous order history)
        else {
            fireStore
                .collection(parameters.collection)
                .document(parameters.document)
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        onFailure()
                    }

                    var items = emptyList<Order>()
                    snapshot?.let {
                        val data = it.data as? HashMap<String, HashMap<String, Any>>
                        items = buildItems(data, parameters.document)
                    }

                    onSuccess(items)
                }
        }
    }

    private fun buildItems(data: HashMap<String, HashMap<String, Any>>?, id: String) : MutableList<Order> {
        val list = mutableListOf<Order>()
        data?.forEach {
            val drinks = buildDrinks(it.value.get("drinks") as ArrayList<Map<String, Any>>)
            val shisha = buildShishas(it.value.get("shisha") as ArrayList<Map<String, Any>>)
            val timeStamp = it.value.get("timeStamp") as Long
            val status = OrderStatus.getStatus(it.value.get("status") as String)
            val order = Order(drinks, shisha, timeStamp, status)
            order.id = id
            list.add(order)
        }

        return list
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
}

