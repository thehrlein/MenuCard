package com.tobiapplications.menu.utils.general

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tobias.hehrlein on 2019-06-24.
 */
object DateUtils {

    fun getDate(milliseconds: Long) : String {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val cal = Calendar.getInstance()
        cal.timeInMillis = milliseconds
        return sdf.format(cal.time)
    }
}