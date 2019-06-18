package com.tobiapplications.menu.utils.repository.drinks

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.tobiapplications.menu.model.admin.Drink
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.repository.base.NetworkSourceDelegate
import io.reactivex.Single
import io.reactivex.SingleEmitter
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class DrinksNetworkSource @Inject constructor(private val fireStore: FirebaseFirestore) :
    NetworkSourceDelegate<Boolean, List<Drink>> {

    override fun requestData(input: Boolean): Single<Response<List<Drink>>> {
        return Single.create { emitter ->
            fireStore.collection(Constants.DRINK_COLLECTION)
                .get()
                .addOnSuccessListener { onSuccess(emitter, it) }
                .addOnFailureListener { emitter.onError(it) }
        }
    }

    private fun onSuccess(emitter: SingleEmitter<Response<List<Drink>>>, snapshot: QuerySnapshot) {
        val drinks = mutableListOf<Drink>()

        for (doc in snapshot) {
            val drink = doc.toObject(Drink::class.java)
            drink.id = doc.id
            drinks.add(drink)
        }

        emitter.onSuccess(Response.success(drinks))
    }

}