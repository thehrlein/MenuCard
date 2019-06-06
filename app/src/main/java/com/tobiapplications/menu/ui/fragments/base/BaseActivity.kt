package com.tobiapplications.menu.ui.fragments.base

import android.os.Bundle
import com.tobiapplications.menu.utils.mvvm.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 17.01.19.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        init()

    }

    abstract fun getLayout() : Int
    abstract fun init()
}