package com.tobiapplications.menu.ui.fragments.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

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