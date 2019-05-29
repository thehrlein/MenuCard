package com.tobiapplications.menu.ui.main

import androidx.lifecycle.ViewModel
import com.tobiapplications.menu.utils.mvvm.SingleLiveEvent
import javax.inject.Inject

/**
 * Created by tobias.hehrlein on 17.09.18.
 */
class OrderOverviewViewModel @Inject constructor(): ViewModel() {

    companion object {
        // This should be a value between 0 and 1, coinciding with a point between the bottom
        // sheet's collapsed (0) and expanded (1) states.
        private const val ALPHA_CHANGEOVER = 0.33f
        // Threshold for when normal header views reach maximum alpha. Should be a value between
        // [ALPHA_CHANGEOVER] and 1, inclusive.
        private const val ALPHA_HEADER_MAX = 0.67f

        private const val ALPHA_CLEAR_ICON_MAX = 0.33f
        private const val ALPHA_CLEAR_ICON_CHANGEOVER = 0.2f
    }

    var headerAlpha = SingleLiveEvent(1f)
    var clearOrderAlpha = SingleLiveEvent(1f)

    fun updateFilterHeadersAlpha(slideOffset: Float) {
        // Alpha of normal header views increases as the sheet expands, while alpha of description
        // views increases as the sheet collapses. To prevent overlap, we use a threshold at which
        // the views "trade places".
        headerAlpha.value = offsetToAlpha(slideOffset, ALPHA_CHANGEOVER, ALPHA_HEADER_MAX)
    }

    /**
     * Map a slideOffset (in the range `[-1, 1]`) to an alpha value based on the desired range.
     * For example, `offsetToAlpha(0.5, 0.25, 1) = 0.33` because 0.5 is 1/3 of the way between 0.25
     * and 1. The result value is additionally clamped to the range `[0, 1]`.
     */
    private fun offsetToAlpha(value: Float, rangeMin: Float, rangeMax: Float): Float {
        return ((value - rangeMin) / (rangeMax - rangeMin)).coerceIn(0f, 1f)
    }
}