package com.tobiapplications.menu.utils.extensions

import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseActivity
import com.tobiapplications.menu.ui.fragments.base.BaseFragment

/**
 * Acitivity extension functions
 */
/**
 * Obtain viewModel. Automatically takes the correct viewModel based on it's fragment variable declaration
 * Bound to fragment scope.
 */
inline fun <reified VM : ViewModel> BaseActivity.obtainViewModel() =
        ViewModelProviders.of(this, factory).get(VM::class.java)


fun AppCompatActivity.toast(message : String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, addToStack: Boolean = true) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.fragment_container, fragment)
    if (addToStack) {
        transaction.addToBackStack(null)
    }
    transaction.commitAllowingStateLoss()
}

//inline fun <reified VM : ViewModel> BaseActivity.obtainViewModel() =
//        ViewModelProviders.of(this, factory).get(VM::class.java)

/**
 * Post the runnable with given delay
 */
fun BaseActivity.postDelayed(runnable: Runnable, delay : Long) {
   Handler().postDelayed(runnable, delay)
}
