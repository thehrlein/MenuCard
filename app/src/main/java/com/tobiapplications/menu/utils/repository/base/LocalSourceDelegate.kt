package com.tobiapplications.menu.utils.repository.base

import io.reactivex.Single
import retrofit2.Response

/**
 * Created by tobias.hehrlein on 10.01.19.
 */
interface LocalSourceDelegate<I, T> : NetworkSourceDelegate<I, T> {

    fun get() : T?
    fun set(response: T?)
    fun dataCached() : Boolean {
        return get() != null
    }
    fun setData(response: Response<T>) {
        if (response.isSuccessful) {
            set(response.body())
        }
    }
    fun clear() {
        set(null)
    }

    override fun requestData(input: I): Single<Response<T>> {
        return Single.just(Response.success(get()))
    }
}