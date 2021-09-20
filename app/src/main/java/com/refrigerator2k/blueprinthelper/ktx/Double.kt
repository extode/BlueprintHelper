package com.refrigerator2k.blueprinthelper.ktx

fun Double.toPrettyString(): String {
    val str = this.toString()
    if (!str.endsWith(".0"))
        return str

    return str.substring(0, str.length - 2)
}