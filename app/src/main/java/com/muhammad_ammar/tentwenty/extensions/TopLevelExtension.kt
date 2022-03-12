package com.muhammad_ammar.tentwenty.extensions

import androidx.work.Constraints
import androidx.work.NetworkType
import com.muhammad_ammar.tentwenty.util.Event

fun <T> T.wrapWithEvent() = Event(this)

val internetWorkerConstraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
    .build()