package com.tobiapplications.menu.ui.activitys

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.model.authentication.LoginStateChanged
import com.tobiapplications.menu.utils.general.CoreService
import com.tobiapplications.menu.utils.general.OnNextObserver
import rx.Observer
import javax.inject.Inject
import kotlin.math.log

/**
 * Created by tobias.hehrlein on 04.06.2019.
 */
class MainActivityViewModel @Inject constructor(firebaseAuth: FirebaseAuth,
                                                private val coreService: CoreService) : ViewModel() {

    val loginStateChanged = MutableLiveData<LoginStateChanged>()
    var title = MutableLiveData<String>()
    var toolbarMenuRes = MutableLiveData<Int>()
    var toolbarMenuListener = MutableLiveData<Toolbar.OnMenuItemClickListener>()
    private var currentLogInStatus : Boolean? = null

    init {
        firebaseAuth.addAuthStateListener {
            val loggedIn = it.currentUser != null
            val hasLoggedIn = currentLogInStatus == false && loggedIn
            val hasLoggedOut = currentLogInStatus == true && !loggedIn
            currentLogInStatus = loggedIn
            loginStateChanged.value = LoginStateChanged(hasLoggedIn = hasLoggedIn, hasLoggedOut = hasLoggedOut)
        }

        observeTitle()
        observeMenuLayoutRes()
        observeMenuToolbarListener()
    }

    private fun observeTitle() {
//        coreService.subscribeTitle(object : OnNextObserver<String> {
//            override fun onNext(t: String) {
//                title.value = t
//            }
//        })
    }

    private fun observeMenuLayoutRes() {
        coreService.subscribeToolbarMenu(object : Observer<Int> {
            override fun onError(e: Throwable?) {
                val a = 1
            }

            override fun onNext(t: Int?) {
                toolbarMenuRes.value = t
            }

            override fun onCompleted() {
                val a = 1
            }
        })
//        coreService.subscribeToolbarMenu(object : Observer<Int> {
//            override fun onNext(t: Int) {
//                toolbarMenuRes.value = t
//            }
//
//            override fun onComplete() {
//
//            }
//
//            override fun onSubscribe(d: Disposable) {
//
//            }
//
//            override fun onError(e: Throwable) {
//
//            }
//        })
//        coreService.subscribeToolbarMenu(object: OnNextObserver<Int> {
//            override fun onNext(t: Int) {
//                toolbarMenuRes.value = t
//            }
//        })
    }

    private fun observeMenuToolbarListener() {
//        coreService.subscribeToolbarMenuListener(object: OnNextObserver<Toolbar.OnMenuItemClickListener> {
//            override fun onNext(t: Toolbar.OnMenuItemClickListener) {
//                toolbarMenuListener.value = t
//            }
//        })
    }
}
