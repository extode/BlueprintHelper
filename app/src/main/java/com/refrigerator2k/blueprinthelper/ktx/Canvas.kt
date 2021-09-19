package com.refrigerator2k.blueprinthelper.ktx

import android.graphics.Canvas
import android.graphics.Paint

fun Canvas.drawLineStrip(points: FloatArray, paint: Paint) {
    if (points.size < 4) {
        return
    }

    for (i in 3 until points.size step 2) {
        val x0 = points[i-3]
        val y0 = points[i-2]
        val x1 = points[i-1]
        val y1 = points[i]
        drawLine(x0, y0, x1, y1, paint)
    }
}
