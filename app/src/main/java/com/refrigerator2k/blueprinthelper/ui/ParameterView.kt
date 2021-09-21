package com.refrigerator2k.blueprinthelper.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import com.refrigerator2k.blueprinthelper.R

class ParameterView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val _paramNameText: TextView
    private val _paramValueText: TextView

    init {
        orientation = HORIZONTAL

        LayoutInflater.from(context).inflate(R.layout.parameter_view, this)
        _paramNameText = findViewById(R.id.parameterName)
        _paramValueText = findViewById(R.id.parameterValue)

        if (attributeSet != null) {
            val ta = context.obtainStyledAttributes(attributeSet, R.styleable.ParameterView)
            try {
                setParamName(ta.getString(R.styleable.ParameterView_paramName) ?: "")
                setParamValue(ta.getString(R.styleable.ParameterView_paramValue) ?: "")
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