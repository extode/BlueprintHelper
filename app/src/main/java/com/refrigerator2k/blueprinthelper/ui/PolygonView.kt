package com.refrigerator2k.blueprinthelper.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.refrigerator2k.blueprinthelper.R
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
        private const val POLYGON_DEFAULT_COLOR = "#4f4f4f"
        private const val POLYGON_DEFAULT_STROKE_WIDTH = 4.0f

        private const val CIRCLE_DEFAULT_COLOR = "#616161"
        private const val CIRCLE_DEFAULT_STROKE_WIDTH = 1.0f

        private const val DEFAULT_VERTICES_COUNT = 5
    }

    private var polygonPaint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.style = Paint.Style.STROKE
        it.strokeCap = Paint.Cap.ROUND
    }
    private var circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.style = Paint.Style.STROKE
    }



    private var _verticesCount = DEFAULT_VERTICES_COUNT
    var verticesCount: Int
        get() = _verticesCount
        set(value) {
            _verticesCount = value
            recalculate()
            invalidate()
        }

    private var radius: Float = 0.0f
    private var points = FloatArray(0)
    private var inset = 0.0f

    init {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.PolygonView)

            try {
                val polygonColor = ta.getColor(
                    R.styleable.PolygonView_pv_polygon_color,
                    Color.parseColor(POLYGON_DEFAULT_COLOR)
                )
                val polygonStrokeWidth = context.dpToPx(
                    ta.getDimension(
                        R.styleable.PolygonView_pv_polygon_stroke_width,
                        POLYGON_DEFAULT_STROKE_WIDTH
                    )
                )

                polygonPaint.apply {
                    color = polygonColor
                    strokeWidth = polygonStrokeWidth
                }

                val circleColor = ta.getColor(
                    R.styleable.PolygonView_pv_circle_color,
                    Color.parseColor(CIRCLE_DEFAULT_COLOR)
                )

                val circleStrokeWidth = context.dpToPx(
                    ta.getDimension(
                        R.styleable.PolygonView_pv_circle_stroke_width,
                        CIRCLE_DEFAULT_STROKE_WIDTH
                    )
                )

                circlePaint.apply {
                    color = circleColor
                    strokeWidth = circleStrokeWidth
                }

                _verticesCount = ta.getInteger(R.styleable.PolygonView_pv_vertices_count, DEFAULT_VERTICES_COUNT)

            } finally {
                ta.recycle()
            }
        }

        inset = polygonPaint.strokeWidth / 2.0f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = min(w / 2.0f, h / 2.0f) - inset
        recalculate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(width / 2.0f, height / 2.0f, radius, circlePaint)
        canvas?.drawLineStrip(points, polygonPaint)
    }

    private fun recalculate() {
        points = FloatArray(_verticesCount * 2 + 2)
        val cx = width / 2
        val cy = height / 2

        val angle = (360.0f / _verticesCount).deg2rad()
        for (i in 0 until _verticesCount + 1) {
            val x = cos(angle * i) * radius + cx
            val y = sin(angle * i) * radius + cy
            points[i * 2] = x
            points[i * 2 + 1] = y
        }
    }
}