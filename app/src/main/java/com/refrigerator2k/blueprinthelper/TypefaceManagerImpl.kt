package com.refrigerator2k.blueprinthelper

import android.content.res.AssetManager
import android.graphics.Typeface

class TypefaceManagerImpl(private val assets: AssetManager) : TypefaceManager {
    private val fontMap = HashMap<String, Typeface>()

    override fun getTypeface(name: String): Typeface {
        var font = fontMap[name]
        if (font == null) {
            font = Typeface.createFromAsset(assets, name)
            fontMap[name] = font
        }
        return font!!
    }
}