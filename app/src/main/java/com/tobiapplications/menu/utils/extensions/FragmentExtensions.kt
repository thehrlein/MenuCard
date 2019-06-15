package com.tobiapplications.menu.utils.extensions

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.tobiapplications.menu.R
import com.tobiapplications.menu.ui.fragments.base.BaseFragment
import timber.log.Timber

/**
 * Created by tobias.hehrlein on 17.01.19.
 *
 * Divided into fragment and dialog fragment methods
 */


/**
 *  < =============================== Fragments =============================== >
 */

/**
 * Obtain viewModel. Automatically takes the correct viewModel based on it's fragment variable declaration
 * Bound to fragment scope.
 */
inline fun <reified VM : ViewModel> BaseFragment.obtainViewModel() =
        ViewModelProviders.of(this, factory).get(VM::class.java)

/**
 * @see obtainViewModel but takes activity as argument to get same instance of the view model
 * Bound to activity scope
 */
inline fun <reified VM : ViewModel> BaseFragment.obtainViewModel(activity: FragmentActivity) =
        ViewModelProviders.of(activity, factory).get(VM::class.java)

/**
 * Create a toast by simple calling "toast(message)"
 * @param message non-null int res
 */
fun BaseFragment.toast(messageRes : Int) {
    toast(getString(messageRes))
}

/**
 * Create a toast by simple calling "toast(message)"
 * @param message non-null string
 */
fun BaseFragment.toast(message : String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

/**
 * Create a log by simple calling "log(string)"
 * @param message non-null string
 */
fun BaseFragment.log(message : String) {
    Timber.d(message)
}

/**
 * Create a log by simple calling "log(any)"
 * @param obj non-null any
 */
fun BaseFragment.log(obj : Any?) {
    Timber.d(obj.toString())
}

/**
 * Post the runnable
 */
fun BaseFragment.post(runnable: Runnable) {
    Handler().post { runnable.run() }
}

/**
 * Post the runnable with given delay
 */
fun BaseFragment.postDelayed(f: () -> Unit, delay : Long) {
    Handler().postDelayed(Runnable { f() }, delay)
}

/**
 * Get array from xml
 */
fun BaseFragment.getArray(arrayRes: Int): Array<out String> {
    return resources.getStringArray(arrayRes)
}

/**
 * Get array from xml as list
 */
fun BaseFragment.getArrayAsList(arrayRes: Int): List<String> {
    return resources.getStringArray(arrayRes).toList()
}

/**
 * Get Color from resources
 */
fun BaseFragment.getColor(res: Int) : Int {
    return ContextCompat.getColor(requireContext(), res)
}

fun BaseFragment.addFragment(fragment: BaseFragment) {
    fragmentManager?.beginTransaction()?.add(R.id.fragment_container, fragment)?.commit()
}

fun BaseFragment.replaceFragment(fragment: BaseFragment, container: Int = R.id.fragment_container, addToStack: Boolean = true) {
    fragmentManager?.beginTransaction()?.apply {
        replace(container, fragment)
        if (addToStack) {
            addToBackStack(null)
        }
        commitAllowingStateLoss()
    }
}

fun BaseFragment.getDimen(dimen: Int) : Float {
    return resources.getDimension(dimen)
}

///**
// *  < =============================== Dialog Fragments =============================== >
// */
//
///**
// * Obtain viewModel. Automatically takes the correct viewModel based on it's fragment variable declaration
// * Bound to fragment scope.
// */
//inline fun <reified VM : ViewModel> BaseDialogFragment.obtainViewModel() =
//        ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
//
///**
// * @see obtainViewModel but takes activity as argument to get same instance of the view model
// * Bound to activity scope
// */
//inline fun <reified VM : ViewModel> BaseDialogFragment.obtainViewModel(activity: FragmentActivity) =
//        ViewModelProviders.of(activity, viewModelFactory).get(VM::class.java)
//
//
///**
// * Method for showing a dialog fragment
// */
//fun BaseDialogFragment.show(fragmentTransaction: FragmentTransaction) {
//    show(fragmentTransaction, this::class.java.simpleName)
//}
//
///**
// * Close the dialog fragment if it is added and currently showing
// */
//fun BaseDialogFragment.close() {
//    postDelayed(Runnable{ if (isAdded && dialog != null && dialog.isShowing) { dismissAllowingStateLoss() } }, 50)
//}
//
///**
// * Post the runnable
// */
//fun BaseDialogFragment.post(runnable: Runnable) {
//    Handler().post { runnable.run() }
//}
//
///**
// * Post the runnable with given delay
// */
//fun BaseDialogFragment.postDelayed(runnable: Runnable, delay : Long) {
//    Handler().postDelayed(runnable, delay)
//}
//
///**
// * Create a toast by simple calling "toast(message)"
// * @param message non-null string
// */
//fun BaseDialogFragment.toast(message : String) {
//    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
//}
//
///**
// * Create a log by simple calling "log(string)"
// * @param message non-null string
// */
//fun BaseDialogFragment.log(message : String) {
//    C24Logger.d(message)
//}
//
///**
// * Create a log by simple calling "log(any)"
// * @param obj non-null any
// */
//fun BaseDialogFragment.log(obj : Any) {
//    C24Logger.d(obj.toString())
//}
//
///**
// * Get array from xml
// */
//fun BaseDialogFragment.getArray(arrayRes: Int): Array<out String> {
//    return resources.getStringArray(arrayRes)
//}
//
///**
// * Get array from xml as list
// */
//fun BaseDialogFragment.getArrayAsList(arrayRes: Int): List<String> {
//    return resources.getStringArray(arrayRes).toList()
//}
//
///**
// * Method for dispatching url.
// */
//fun BaseDialogFragment.openLink(navigationService: NavigationService, url: String, stacked: Boolean = true) {
//    navigationService.dispatchAppLink(url, stacked)
//}
//
///**
// * Get Color from resources
// */
//fun BaseDialogFragment.getColor(res: Int) : Int {
//    return ContextCompat.getColor(requireContext(), res)
//}