package com.tobiapplications.menu.utils.repository

import io.reactivex.Single
import retrofit2.Response

/**
 * Created by tobias.hehrlein on 07.01.19.
 */
interface NetworkSourceDelegate<I, T> {

    fun requestData(input: I): Single<Response<T>>
}