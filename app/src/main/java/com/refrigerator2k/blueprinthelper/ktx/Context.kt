package com.refrigerator2k.blueprinthelper.ktx

import android.content.Context

fun Context.dpToPx(dp: Float): Float = dp * resources.displayMetrics.density