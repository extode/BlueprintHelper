package com.refrigerator2k.blueprinthelper

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import kotlin.math.PI
import kotlin.math.round

fun round2(d: Double) = round(d * 100.0) / 100.0

fun simpleTextWatcher(onChanged: (text: CharSequence) -> Unit) = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChanged(s ?: "")
    }

    override fun afterTextChanged(s: Editable?) {
    }
}

fun Context.dpToPx(dp: Float): Float = dp * resources.displayMetrics.density
fun Float.deg2rad(): Float = PI.toFloat() / 180.0f * this
