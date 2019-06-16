package com.tobiapplications.menu.ui.activitys

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tobiapplications.menu.model.authentication.LoginStateChanged
import com.tobiapplications.menu.utils.general.CoreService
import rx.Observer
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 04.06.2019.
 */
class MainActivityViewModel @Inject constructor(firebaseAuth: FirebaseAuth,
                                                private val coreService: CoreService) : ViewModel() {

    val loginStateChanged = MutableLiveData<LoginStateChanged>()
    var title = MutableLiveData<String>()
    var toolbarBackButton = MutableLiveData<Boolean>()
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
        observeMenuBackButton()
        observeMenuLayoutRes()
        observeMenuToolbarListener()
    }

    private fun observeTitle() {
        coreService.subscribeTitle(object : Observer<String> {
            override fun onError(e: Throwable?) {}

            override fun onNext(t: String?) {
                title.value = t
            }

            override fun onCompleted() {}
        })
    }

    private fun observeMenuBackButton() {
        coreService.subscribeToolbarBackButton(object : Observer<Boolean> {
            override fun onError(e: Throwable?) {

            }

            override fun onNext(t: Boolean?) {
                toolbarBackButton.value = t
            }

            override fun onCompleted() {

            }
        })
    }

    private fun observeMenuLayoutRes() {
        coreService.subscribeToolbarMenu(object : Observer<Int> {
            override fun onError(e: Throwable?) {}

            override fun onNext(t: Int?) {
                toolbarMenuRes.value = t
            }

            override fun onCompleted() {}
        })

    }

    private fun observeMenuToolbarListener() {
        coreService.subscribeToolbarMenuListener(object : Observer<Toolbar.OnMenuItemClickListener> {
            override fun onError(e: Throwable?) {}

            override fun onNext(t: Toolbar.OnMenuItemClickListener?) {
                toolbarMenuListener.value = t
            }

            override fun onCompleted() {}
        })
    }
}

