package com.tobiapplications.menu.ui.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 17.01.19.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        init()

    }

    abstract fun getLayout() : Int
    abstract fun init()
}