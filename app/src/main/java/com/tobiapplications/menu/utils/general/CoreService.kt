package com.tobiapplications.menu.utils.general

import androidx.appcompat.widget.Toolbar
import rx.Observer
import rx.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoreService @Inject constructor() {

    private var titleObservable : PublishSubject<String> = PublishSubject.create()
    private var toolbarBackButton : PublishSubject<Boolean> = PublishSubject.create()
    private var toolbarMenu : PublishSubject<Int> = PublishSubject.create()
    private var toolbarMenuListener : PublishSubject<Toolbar.OnMenuItemClickListener> = PublishSubject.create()

    fun setTitle(title : String) {
        titleObservable.onNext(title)
    }

    fun subscribeTitle(observer: Observer<String>) {
        titleObservable.subscribe(observer)
    }

    fun setBackButtonEnabled(enabled: Boolean) {
        toolbarBackButton.onNext(enabled)
    }

    fun subscribeToolbarBackButton(observer: Observer<Boolean>) {
        toolbarBackButton.subscribe(observer)
    }

    fun setToolbarMenu(menuRes : Int) {
        toolbarMenu.onNext(menuRes)
    }

    fun subscribeToolbarMenu(observer : Observer<Int>) {
        toolbarMenu.subscribe(observer)
    }

    fun setToolbarMenuListener(listener: Toolbar.OnMenuItemClickListener?) {
        toolbarMenuListener.onNext(listener)
    }

    fun subscribeToolbarMenuListener(observer : Observer<Toolbar.OnMenuItemClickListener>) {
        toolbarMenuListener.subscribe(observer)
    }
}