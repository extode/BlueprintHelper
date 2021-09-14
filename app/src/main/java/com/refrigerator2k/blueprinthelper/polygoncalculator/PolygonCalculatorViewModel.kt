package com.refrigerator2k.blueprinthelper.polygoncalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.refrigerator2k.blueprinthelper.round2
import kotlin.math.PI
import kotlin.math.sin

class PolygonCalculatorViewModel : ViewModel() {

    private var _radius: Double = 0.0
    private var _anglesCount: Int = 0

    private val _sideLength = MutableLiveData<Double>(0.0)
    val sideLength: LiveData<Double> = _sideLength

    fun setRadius(radius: Double) {
        _radius = radius
        calculate()
    }

    fun setAnglesCount(anglesCount: Int) {
        _anglesCount = anglesCount
        calculate()
    }

    private fun calculate() {
        if (_anglesCount > 0) {
            val angleR = (PI / 180.0) * (180 / _anglesCount)
            _sideLength.value = round2(2 * _radius * sin(angleR))
        }
    }
}