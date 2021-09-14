package com.refrigerator2k.blueprinthelper

import android.content.res.AssetManager

object TypefaceManagerProvider {
    private lateinit var _typefaceManager: TypefaceManager
    val instance: TypefaceManager
        get() = _typefaceManager

    fun init(am: AssetManager) {
        _typefaceManager = TypefaceManagerImpl(am)
    }
}