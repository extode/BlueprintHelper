package com.refrigerator2k.blueprinthelper.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.refrigerator2k.blueprinthelper.deg2rad
import com.refrigerator2k.blueprinthelper.dpToPx
import com.refrigerator2k.blueprinthelper.ktx.drawLineStrip
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class PolygonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {
    companion object {
        private const val TAG = "PolygonView"
    }

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.color = Color.WHITE
        it.style = Paint.Style.STROKE
        it.strokeCap = Paint.Cap.ROUND
        it.strokeWidth = context.dpToPx(2.0f)
    }
    private var vertexCount = 6
    private var radius: Float = 0.0f
    private var points = FloatArray(0)
    private var inset = 0.0f

    init {
        inset = paint.strokeWidth / 2.0f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(w/2.0f, h/2.0f) - inset
        recalculate()
        Log.d(TAG, "onSizeChanged $w $h")
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(width/2.0f, height/2.0f, radius, paint)
        canvas?.drawLineStrip(points, paint)
        super.onDraw(canvas)
    }

    private fun recalculate() {
        points = FloatArray(vertexCount*2+2)
        val cx = width/2
        val cy = height/2

        val angle = (360.0f / vertexCount).deg2rad()
        for (i in 0 until vertexCount+1) {
            val x = cos(angle*i) * radius + cx
            val y = sin(angle*i) * radius + cy
            points[i*2] = x
            points[i*2+1] = y
        }
    }
}