package com.axppress.maccabitest.utils

object Utils {
    fun String.firstCap() = this.replaceFirstChar { it.uppercase() }
}