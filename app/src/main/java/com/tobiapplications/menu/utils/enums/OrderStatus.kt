package com.tobiapplications.menu.utils.enums

import com.tobiapplications.menu.R

/**
 *  Created by tobiashehrlein on 2019-05-23
 */
enum class OrderStatus(val textRes: Int, val colorRes: Int)  {

    NEW(R.string.general_order_status_new, R.color.colorGrey),
    SENT(R.string.general_order_status_sent, R.color.colorAqua),
    IN_PROGRESS(R.string.general_order_status_in_progress, R.color.colorBlue),
    DONE(R.string.general_order_status_done, R.color.colorGreen);

    companion object {
        fun getPrevStatus(status: OrderStatus) : OrderStatus? {
            return when (status) {
                NEW -> null
                SENT -> NEW
                IN_PROGRESS -> SENT
                DONE -> IN_PROGRESS
            }
        }

        fun getNextStatus(status: OrderStatus) : OrderStatus? {
            return when (status) {
                NEW -> SENT
                SENT -> IN_PROGRESS
                IN_PROGRESS -> DONE
                DONE -> null
            }
        }
    }
}