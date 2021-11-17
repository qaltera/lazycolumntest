package com.qaltera.lazycolumntest.ui.theme

import androidx.compose.runtime.Immutable

/*
 * ************************************************
 * TestDataBlock
 * Date: 2021-11-17
 * ------------------------------------------------
 * Copyright (C) SPB TV AG 2007-2021 - All Rights Reserved
 * Author: Yulia Rogovaya
 * ************************************************
 */
@Immutable
data class TestDataBlock(
    val id: String,
    val data: Int
) {
    val i: Int = instancesTotal++
    init {
        //instancesTotal++
        instances++
        println("[MC] TestDataBlock create instance instances=$instances" +
            "total=$instancesTotal")
    }
    protected fun finalize() {
        println("[MC] TestDataBlock finalize instance num=$i" +
            "total=$instancesTotal")
        instances--
    }

    companion object {
        var instances: Int = 0
        var instancesTotal: Int = 0
    }
}