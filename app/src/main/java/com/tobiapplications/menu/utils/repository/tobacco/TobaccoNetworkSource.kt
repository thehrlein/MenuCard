package com.tobiapplications.menu.utils.repository.tobacco

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.tobiapplications.menu.model.admin.Tobacco
import com.tobiapplications.menu.utils.general.Constants
import com.tobiapplications.menu.utils.repository.base.NetworkSourceDelegate
import io.reactivex.Single
import io.reactivex.SingleEmitter
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 2019-06-17.
 */
class TobaccoNetworkSource @Inject constructor(private val fireStore: FirebaseFirestore) :
    NetworkSourceDelegate<Boolean, List<Tobacco>> {

    override fun requestData(input: Boolean): Single<Response<List<Tobacco>>> {
        return Single.create { emitter ->
            fireStore.collection(Constants.TOBACCO_COLLECTION)
                .get()
                .addOnSuccessListener { onSuccess(emitter, it) }
                .addOnFailureListener { emitter.onError(it) }
        }
    }

    private fun onSuccess(emitter: SingleEmitter<Response<List<Tobacco>>>, snapshot: QuerySnapshot) {
        val tobaccos = mutableListOf<Tobacco>()

        for (doc in snapshot) {
            val tobacco = doc.toObject(Tobacco::class.java)
            tobacco.fireId = doc.id
            tobaccos.add(tobacco)
        }

        emitter.onSuccess(Response.success(tobaccos))
    }
}