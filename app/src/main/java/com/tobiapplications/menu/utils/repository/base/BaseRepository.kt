package com.tobiapplications.menu.utils.repository.base

import io.reactivex.Observable
import retrofit2.Response
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by tobias.hehrlein on 10.01.19.
 */
open class BaseRepository<I, T> constructor(private val localSource: LocalSourceDelegate<I, T>,
                                            private val networkSource: NetworkSourceDelegate<I, T>) : RepositoryDelegate<I, T> {

    private val REQUEST_CODE = "request_code"
    private val requestCache = ConcurrentHashMap<String, Observable<Response<T>>>()

    override fun getData(input: I): Observable<Response<T>> {
        if (localSource.dataCached()) {
            return localSource.requestData(input).toObservable()
        }

        if (requestCache[REQUEST_CODE] is Observable<Response<T>>) {
            return requestCache[REQUEST_CODE]!!
        }

        val request = networkSource.requestData(input)
                .doFinally { requestCache.remove(REQUEST_CODE) }
                .doOnSuccess { localSource.setData(it) }
                .toObservable()
                .share()
                .replay()
                .autoConnect(1)

        requestCache[REQUEST_CODE] = request

        return request
    }

    override fun clear() {
        localSource.clear()
    }
}