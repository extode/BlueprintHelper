package com.refrigerator2k.blueprinthelper.parameter

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import com.refrigerator2k.blueprinthelper.R

class TextParameter(
    context: Context,
    attributeSet: AttributeSet?,
    @AttrRes defStyleAttr: Int,
    @StyleRes defStyleRes: Int) : FrameLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0, 0)

    private val _paramNameText: TextView
    private val _paramValueText: TextView

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.parameter_view, this)
        _paramNameText = view.findViewById(R.id.parameterName)
        _paramValueText = view.findViewById(R.id.parameterValue)

        if (attributeSet != null) {
            val ta = context.obtainStyledAttributes(attributeSet, R.styleable.TextParameter, 0, 0)
            try {
                setParamName(ta.getString(R.styleable.TextParameter_paramName) ?: "")
                setParamValue(ta.getString(R.styleable.TextParameter_paramValue) ?: "")
            } finally {
                ta.recycle()
            }
        }
    }


    fun setParamName(name: String) {
        _paramNameText.text = name
    }

    fun setParamValue(value: String) {
        _paramValueText.text = value
    }
}