package com.tobiapplications.menu.ui.main

import android.view.Menu
import android.view.MenuItem
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.base.BaseActivity
import com.tobiapplications.menu.utils.extensions.replaceFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun init() {
        setSupportActionBar(toolbar)

        replaceFragment(MainFragment.newInstance(), false)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
