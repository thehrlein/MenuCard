package com.tobiapplications.menu.utils.repository

import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by tobias.hehrlein on 10.01.19.
 */
interface RepositoryDelegate<I, T> {

    fun getData(input: I) : Observable<Response<T>>
    fun clear()
}