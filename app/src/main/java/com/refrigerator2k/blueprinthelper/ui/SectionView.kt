package com.refrigerator2k.blueprinthelper.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.refrigerator2k.blueprinthelper.R

class SectionView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr, R.style.Widget_BlueprintHelper_SectionView) {

    companion object {
        private const val TAG = "SectionView"
    }

    private val titleText: TextView
    private val icon: ImageView
    private val nextButton: ImageButton

    fun setOnExpandAction(onExpand: (() -> Unit)) {
        nextButton.visibility = View.VISIBLE
        nextButton.setOnClickListener {
            onExpand()
        }
    }

    var title: String
        get() = titleText.text.toString()
        set(value) {
            titleText.text = value
        }

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.section_view, this)

        titleText = findViewById(R.id.section_view_title)
        icon = findViewById(R.id.section_view_icon)
        nextButton = findViewById(R.id.section_view_button_next)

        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.SectionView)
            try {
                title = ta.getString(R.styleable.SectionView_sv_title) ?: ""

                val drawable = ta.getDrawable(R.styleable.SectionView_sv_drawable)
                setDrawable(drawable)
            } finally {
                ta.recycle()
            }
        }
    }


    fun setDrawable(@DrawableRes drawableRes: Int) {
        icon.setImageDrawable(ContextCompat.getDrawable(context, drawableRes))
    }

    fun setDrawable(drawable: Drawable?) {
        icon.setImageDrawable(drawable)
    }
}