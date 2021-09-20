package com.refrigerator2k.blueprinthelper.polygoncalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.refrigerator2k.blueprinthelper.ktx.toPrettyString
import com.refrigerator2k.blueprinthelper.round2
import kotlin.math.PI
import kotlin.math.sin

class PolygonCalculatorViewModel : ViewModel() {
    companion object {
        private const val INT_NULL = -1
        private const val DOUBLE_NULL = Double.NEGATIVE_INFINITY
    }

    private val _radius = MutableLiveData<Double>(DOUBLE_NULL)
    val radius: LiveData<Double> = _radius

    val radiusStr: String
        get() {
            return if (_radius.value != DOUBLE_NULL) {
                _radius.value!!.toPrettyString()
            }
            else
                ""
        }

    private val _anglesCount = MutableLiveData<Int>(INT_NULL)
    val anglesCount: LiveData<Int> = _anglesCount

    val anglesCountStr: String
        get() {
            return if (_anglesCount.value != INT_NULL)
                _anglesCount.value.toString()
            else
                ""
        }

    private val _sideLength = MutableLiveData<Double>(DOUBLE_NULL)
    val sideLength: LiveData<Double> = _sideLength

    val sideLengthStr: LiveData<String> = Transformations.map(_sideLength) {
        if (it != DOUBLE_NULL)
            round2(it).toPrettyString()
        else
            "0"
    }

    private val _tooMuchVerticesErrorEvent = MutableLiveData<Boolean>(false)
    val tooMuchVerticesErrorEvent: LiveData<Boolean> = _tooMuchVerticesErrorEvent

    fun setRadius(value: String) {
        _radius.value = value.toDoubleOrNull() ?: 0.0
        calculate()
    }

    fun setAnglesCount(anglesCount: Int) {
        if (anglesCount < 32) {
            _tooMuchVerticesErrorEvent.setValueIfChanged(false)
            _anglesCount.value = anglesCount
            calculate()
        } else {
            _tooMuchVerticesErrorEvent.setValueIfChanged(true)
        }
    }

    private fun calculate() {
        if (radius.value == DOUBLE_NULL || _anglesCount.value == INT_NULL)
            return

        val n = _anglesCount.value!!
        val radius = this.radius.value!!
        if (n > 0) {
            val angleR = PI / n
            _sideLength.value = round2(2.0 * radius * sin(angleR))
        }
    }

    private fun <T> MutableLiveData<T>.setValueIfChanged(newValue: T) {
        if (value != newValue)
            value = newValue
    }
}
