package com.refrigerator2k.blueprinthelper

import androidx.fragment.app.Fragment

abstract class SectionController {
    var isExpanded: Boolean = false

    abstract fun onSetup(owner: Fragment)
}